package io.github.kahenteikou.kotlinoder.instrumentation

import com.intellij.openapi.util.Disposer
import com.intellij.psi.PsiManager
import com.intellij.testFramework.LightVirtualFile
import io.github.kahenteikou.kotlinoder.lang.VLangUtils
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.diagnostics.rendering.renderParameter
import org.jetbrains.kotlin.idea.KotlinFileType
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.psi.classVisitor
import org.jetbrains.kotlin.psi.psiUtil.containingClass

class Scope2Code {
    companion object{
        @JvmStatic
        fun getCode(scope:CompilationUnitDeclaration):String{
            var renderer=CompilationUnitRenderer(
                ClassDeclarationRenderer(
                    MethodDeclarationRenderer(
                        InvocationCodeRenderer()
                    )
                )
            )
            return renderer.render(scope)
        }
        @JvmStatic
        fun getCode(scope:ClassDeclaration):String{
            var renderer=ClassDeclarationRenderer(
                MethodDeclarationRenderer(
                    InvocationCodeRenderer()
                )
            )
            return renderer.render(scope)
        }
        @JvmStatic
        fun getCode(scope:MethodDeclaration):String{
            var renderer=MethodDeclarationRenderer(
                InvocationCodeRenderer()
            )
            return renderer.render(scope)
        }
        @JvmStatic
        fun getCode(scope:Invocation):String{
            var renderer=InvocationCodeRenderer()
            return renderer.render(scope)
        }
        @JvmStatic
        fun demoScope():CompilationUnitDeclaration{
            var builder:VisualCodeBuilder=VisualCodeBuilder_Impl()
            var myFile:CompilationUnitDeclaration=builder.declareCompilationUnit(
                "MyFile.kt","my.testpackage"
            )
            var myFileClass:ClassDeclaration=builder.declareClass(
                myFile,Type("my.testpackage.MyFileClass"),
                Modifiers(Modifier.PUBLIC),Extends(),Extends()
            )
            builder.createVariable(myFileClass,Type("Int"),"value1")
            var m1:MethodDeclaration=builder.declareMethod(myFileClass,
            Modifiers(Modifier.PUBLIC),Type("Int"),"m1",
                Parameters(Parameter(Type("Int"),"v1")))
            builder.invokeMethod(m1,"this",m1.getName(),true,"retM1a",m1.getVariable("v1")!!)

            var mx:MethodDeclaration=builder.declareMethod(myFileClass,
                Modifiers(Modifier.PUBLIC),Type("void"),"mx",
                Parameters(Parameter(Type("Int"),"v1")))
            builder.invokeStaticMethod(mx,Type(""),"println",true,"",mx.getVariable("v1")!!)

            return myFile
        }
        @JvmStatic
        fun main(args:Array<String>){
            var scope:CompilationUnitDeclaration=demoScope()
            var renderer:CompilationUnitRenderer=CompilationUnitRenderer(
                ClassDeclarationRenderer(
                    MethodDeclarationRenderer(
                        InvocationCodeRenderer()
                    )
                )
            )
            println(renderer.render(scope))
            println("demo")
            //kotlin parser

            val config= CompilerConfiguration()
            var dispos= Disposer.newDisposable()
            val env= KotlinCoreEnvironment.createForProduction(
                dispos,
                config,
                EnvironmentConfigFiles.JVM_CONFIG_FILES
            )
            var ksfactory = KtPsiFactory(env.project)
            //var psif= ksfactory.createFile(renderer.render(scope))
            var buffile=LightVirtualFile(scope.getFileName()!!, KotlinFileType.INSTANCE,renderer.render(scope))
            var psif=PsiManager.getInstance(env.project).findFile(buffile) as KtFile
            var parserkun = KotlinCodeVisitor(psif,VisualCodeBuilder_Impl())
            parserkun.parse(psif)
        }
    }
}
final class Utils {
    companion object{
        private var modifierNames:MutableMap<Modifier,String> = HashMap()
        init{
            modifierNames[Modifier.ABSTRACT] = "abstract"
            modifierNames[Modifier.FINAL]="final"
            modifierNames[Modifier.PRIVATE]="private"
            modifierNames[Modifier.PROTECTED]="protected"
            modifierNames[Modifier.PUBLIC]="public"
            modifierNames[Modifier.STATIC]="@JvmStatic"
        }
        fun modifierToName(m:Modifier):String{
            return modifierNames.get(m)!!
        }
    }
    private constructor(){
        throw AssertionError()
    }

}
class InvocationCodeRenderer :CodeRenderer<Invocation>{
    constructor(){

    }

    override fun render(entity: Invocation): String {
        var cb=CodeBuilder()
        render(entity,cb)
        return cb.getCode()
    }
    override fun render(i: Invocation, cb: CodeBuilder) {
        if(i.isConstructor()){
            cb.append(" ").append(i.getReturnValueName()).
                    append(" = ").append(i.getVariableName()!!).
                    append("(")
            renderParams(i,cb)
            cb.append(")")
        }else if(!i.isScope()){
            cb.
                    append(i.getVariableName()!!).
                    append(".").
                    append(i.getMethodName()).append("(")
            renderParams(i,cb)
            cb.append(")")
        }else{
            var si:ScopeInvocation=i as ScopeInvocation
            var s:Scope=si.getScope()
            if(s is ForDeclaration){
                cb.append("for( ").append(s.getVarName()).
                        append(" in ").append(s.getFrom().toString())
                if(s.getInc()>0){
                    cb.append(" until ").append(s.getTo().toString())
                }else{
                    cb.append(" downTo ").append(s.getTo().toString())
                }
                if(s.getInc()==1 || s.getInc()==-1){
                    //no
                    cb.append("")
                }else if(s.getInc() < 0){
                    cb.append(" step ").append(s.getInc().toString().replace("-",""))
                }else{
                    cb.append(" step ").append(s.getInc().toString())
                }
                cb.append(") {")
                if(!s.getControlFlow().getInvocations().isEmpty()){
                    cb.newLine()
                    cb.incIndentation()
                }
                for(j in s.getControlFlow().getInvocations()){
                    render(j,cb)
                }
                if(!s.getControlFlow().getInvocations().isEmpty()){
                    cb.decIndentation()
                }
                cb.append("}")
            }
        }
        cb.newLine()
    }
    fun renderParams(e:Invocation,cb:CodeBuilder){
        var firstCall:Boolean=true
        for(v in e.getArguments()){
            if(firstCall){
                firstCall=false
            }else{
                cb.append(", ")
            }
            if(v!!.isConstant()!!){
                var constString:String?=null
                if(v.getType().equals(Type("kotlin.String"))){
                    constString="\""+VLangUtils.addEscapeCharsToCode(v!!.getValue().toString())+"\""
                }else{
                    constString=v!!.getValue().toString()
                }
                cb.append(constString)
            }else{
                cb.append(v.getName()!!)
            }
        }
    }

}
class MethodDeclarationRenderer : CodeRenderer<MethodDeclaration>{
    private var invocationRenderer:CodeRenderer<Invocation>
    constructor(invocationRenderer:CodeRenderer<Invocation>){
        this.invocationRenderer=invocationRenderer
    }

    override fun render(entity: MethodDeclaration): String {
        var cb:CodeBuilder=CodeBuilder()
        render(entity,cb)
        return cb.toString()
    }

    override fun render(entity: MethodDeclaration, cb: CodeBuilder) {
        createModifiers(entity,cb)
        cb.append("fun ")
        //cb.append(entity.getReturnType()!!.getFullClassName()!!)
        cb.append(entity.getName()).append("(")
        renderParams(entity,cb)
        cb.append(")")
        if(!entity.getReturnType().equals(Type("void"))){
            cb.append(": ").append(entity.getReturnType().getFullClassName()!!)
        }
        cb.append("{").newLine()
        cb.incIndentation()
        for(i:Invocation in entity.getControlFlow().getInvocations()){
            println(" --> inv: $i")
            invocationRenderer.render(i,cb)
        }
        cb.decIndentation().append("}").newLine()
    }
    private fun createModifiers(md:MethodDeclaration,cb:CodeBuilder){
        for(m in md.getModifiers().getModifiers()){
            cb.append(Utils.modifierToName(m)).append(" ")
        }
    }
    private fun renderParams(e:MethodDeclaration,cb:CodeBuilder){
        var firstCall:Boolean=true
        for(v:IParameter in e.getParameters().getParamenters()){
            if(firstCall){
                firstCall=false
            }else{
                cb.append(", ")
            }
            //cb.append(v.getType().getFullClassName()!!).append(" ").append(v.getName()!!)
            cb.append(v.getName()!!).append(" : ").append(v.getType().getFullClassName()!!)
        }
    }

}
class ClassDeclarationRenderer :CodeRenderer<ClassDeclaration> {
    private var methodDeclarationRenderer: CodeRenderer<MethodDeclaration>

    constructor(methodDeclarationRenderer: CodeRenderer<MethodDeclaration>) {
        this.methodDeclarationRenderer = methodDeclarationRenderer
    }

    override fun render(entity: ClassDeclaration): String {
        var cb: CodeBuilder = CodeBuilder()
        render(entity, cb)
        return cb.getCode()
    }

    override fun render(cd: ClassDeclaration, cb: CodeBuilder) {
        createModifiers(cd,cb)
        cb.append("class ")
        cb.append(Type(cd.getName()).getShortName()!!)
        createExtendsAndImplements(cd,cb)
        cb.append(" {").newLine()
        cb.incIndentation()
        createDeclaredVariables(cd,cb)
        for(md:MethodDeclaration in cd.getDeclaredMethods()){
            methodDeclarationRenderer.render(md,cb)
        }
        cb.decIndentation()
        cb.append("}").newLine()
    }
    private fun createDeclaredVariables(cd:ClassDeclaration,cb:CodeBuilder){
        for(v:Variable in cd.getVariables()){
            if(!"this".equals(v.getName())){
                /*cb.newLine().append(v.getType().getFullClassName()!!).
                        append(" ").append(v.getName()!!).newLine()*/
                cb.newLine().append("var ").append(v.getName()!!).append(" : ")
                    .append(v.getType().getFullClassName()!!).newLine()
            }
        }
        cb.newLine()
    }
    private fun createModifiers(cd:ClassDeclaration,cb:CodeBuilder){
        for(m:Modifier in cd.getClassModifiers().getModifiers()){
            cb.append(Utils.modifierToName(m)).append(" ")
        }
    }
    private fun createExtendsAndImplements(cd:ClassDeclaration,cb:CodeBuilder){
        var types:MutableList<IType> = ArrayList<IType>()
        for(typekun in cd.getImplements().getTypes()){
            types.add(typekun)
        }
        for(typekun2 in cd.getExtends().getTypes()){
            types.add(typekun2)
        }
        if(types.isEmpty()){
            return
        }
        var isFirst:Boolean=true
        for(type:IType in types){
            if(type.getFullClassName().equals("java.lang.Object") || type.getFullClassName().equals("kotlin.Any")){
                continue
            }
            if(isFirst) {
                isFirst = false
                cb.append(" : ")
            }else{
                cb.append(", ")
            }
            cb.append(type.getFullClassName()!!)
        }

    }
}
class CompilationUnitRenderer :CodeRenderer<CompilationUnitDeclaration>{
    private var classDeclarationRenderer:CodeRenderer<ClassDeclaration>?=null
    constructor(){

    }
    constructor(classDeclarationRenderer:CodeRenderer<ClassDeclaration>){
        this.classDeclarationRenderer=classDeclarationRenderer
    }

    override fun render(entity: CompilationUnitDeclaration): String {
        var cb=CodeBuilder()
        render(entity,cb)
        return cb.getCode()
    }

    override fun render(e: CompilationUnitDeclaration, cb: CodeBuilder) {
        if(e.getPackageName()!=null||e.getPackageName()!!.isEmpty()){
            cb.append("package ").append(e.getPackageName()!!).newLine().newLine()
        }
        for(cd in e.getDeclaredClasses()){
            classDeclarationRenderer!!.render(cd,cb)
        }
    }
    fun getClassDeclarationRenderer():CodeRenderer<ClassDeclaration>{
        return classDeclarationRenderer!!
    }
    fun setClassDeclarationRenderer(classDeclarationRenderer:CodeRenderer<ClassDeclaration>){
        this.classDeclarationRenderer=classDeclarationRenderer
    }


}