
package io.github.kahenteikou.kotlinoder.instrumentation

import eu.mihosoft.vrl.workflow.FlowFactory
import eu.mihosoft.vrl.workflow.IdGenerator
import io.github.kahenteikou.kotlinoder.lang.VLangUtils
import ktast.ast.Node
import ktast.ast.Visitor
import ktast.ast.Writer
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.jvm.internal.Intrinsics.Kotlin

fun KotlinVisualizationTransformationVisit(fkun:Node.KotlinFile){
    var codeBuilder:VisualCodeBuilder_Impl= VisualCodeBuilder_Impl()
    var scopes:MutableMap<String,MutableList<Scope>> = HashMap()
    var visitor:KotlinCodeVisitor= KotlinCodeVisitor(fkun,codeBuilder)
    var clsScopes:MutableList<Scope> = ArrayList()
    scopes["rootfile.kt"] = clsScopes
    scopes.get("rootfile.kt")!!.add(visitor.getrootScope()!!)
    visitor.visit(fkun)
    UIBinding.scopes.putAll(scopes)
}
class KotlinCodeVisitor:CustomVisitor{
    private var codeBuilder:VisualCodeBuilder_Impl
    private var ktFile:Node.KotlinFile
    private var rootScope:Scope?=null
    private var currentScope:Scope?=null
    private var lastMethod:Invocation?=null
    private var vIdStack: Stack<String> = Stack()
    private var generator: IdGenerator = FlowFactory.newIdGenerator()
    fun getrootScope():Scope?{
        return rootScope
    }
    constructor(kf:Node.KotlinFile,codeBuilder:VisualCodeBuilder_Impl){
        this.ktFile=kf
        this.codeBuilder=codeBuilder
        codeBuilder.setIdRequest(object : IdRequest{
            override fun request(): String {
                return requestId()
            }

        })

        this.rootScope=codeBuilder.declareCompilationUnit(
            "rootfile.kt",
            "undefined"
        )
        this.currentScope=rootScope
    }
    private fun requestId():String{
        var result:String=""
        if(!vIdStack.isEmpty()){
            result=vIdStack.pop()
            if(generator.ids.contains(result)){
                error(">> requestId(): id already exists: $result")
                result=generator.newId()
            }else{
                generator.addId(result)
                println(">> USING ID: $result")
            }
        }else{
            result=generator.newId()
        }
        return result
    }

    override fun visitPackageDirective(p: Node.PackageDirective, v: Node) {

        var s2:String=""
        var isFirst:Boolean=true
        for(namekun in p.names){
            if(isFirst)
                s2+=namekun.name
            else
                s2+=".${namekun.name}"

            isFirst=false
        }
        (rootScope as? CompilationUnitDeclaration)?.setPackageName(s2)
        super.visitPackageDirective(p, v)
    }
    override fun visitClass(classkun:Node.Declaration.Class, parent:Node){
        if(classkun.name != null) {
            println("CLASS: ${classkun.name?.name}")
            var modifierskun: IModifiers? = null
            classkun.modifiers?.elements?.let {
                modifierskun = convertModifiers(it)
            }
            if (modifierskun == null)
                modifierskun= Modifiers(Modifier.PUBLIC)
            modifierskun?.getModifiers()?.forEach({
                println(">> MODIFIER: $it")
            })
            currentScope=codeBuilder.declareClass(currentScope as CompilationUnitDeclaration,
            Type(classkun.name!!.name,false),
            modifierskun!!,
                convertExtends(classkun.parents),
                Extends()
            )
            super.visitClass(classkun, parent)
            currentScope=currentScope?.getParent()
            currentScope?.setCode(Writer.write(classkun))

        }else{

            super.visitClass(classkun, parent)
        }
    }

    override fun visitFunctionDeclaration(f: Node.Declaration.Function, v: Node) {
        println("FUNCTION: ${f.name?.name}, parentScope: ${currentScope?.getName()}:${currentScope?.getType()?.name}")
        if(currentScope is ClassDeclaration){
            var modifierskun: IModifiers? = null
            f.modifiers?.elements?.let {
                modifierskun = convertModifiers(it)
            }
            if (modifierskun == null)
                modifierskun= Modifiers(Modifier.PUBLIC)
            modifierskun?.getModifiers()?.forEach({
                println(">> MODIFIER: $it")
            })
            var typeName="void"
            if(f.receiverTypeRef?.type is Node.Type.Simple){
                for(ikun in (f.receiverTypeRef?.type as Node.Type.Simple).pieces){
                    typeName=ikun.name.name
                }
            }
            currentScope = codeBuilder.declareMethod(
                currentScope as ClassDeclaration,
                modifierskun!!,
                Type(typeName, true),
                f.name!!.name,
                convertFuncParameters(f.params)
            )
            currentScope!!.setCode(Writer.write(f))

            super.visitFunctionDeclaration(f, v)
            currentScope=currentScope!!.getParent()
            currentScope!!.setCode(Writer.write(f))
        }else{

            super.visitFunctionDeclaration(f, v)
        }
    }

    private fun convertModifiers(modifiers:List<Node.Modifier>):IModifiers{
        var modskun:MutableList<Modifier> = ArrayList()
        for(mkun in modifiers){
            if(mkun is ktast.ast.Node.Modifier.Keyword){
                if(mkun.token==Node.Modifier.Keyword.Token.PUBLIC)
                    modskun.add(Modifier.PUBLIC)
                else if(mkun.token==Node.Modifier.Keyword.Token.PRIVATE)
                    modskun.add(Modifier.PRIVATE)
                else if(mkun.token==Node.Modifier.Keyword.Token.PROTECTED)
                    modskun.add(Modifier.PROTECTED)
                else if(mkun.token==Node.Modifier.Keyword.Token.FINAL)
                    modskun.add(Modifier.FINAL)
                else if(mkun.token == Node.Modifier.Keyword.Token.ABSTRACT)
                    modskun.add(Modifier.ABSTRACT)
            }
        }
        if(!(modskun.contains(Modifier.PUBLIC) || modskun.contains(Modifier.PRIVATE) || modskun.contains(Modifier.PROTECTED))) {
            modskun.add(Modifier.PUBLIC)
        }
        return Modifiers(modskun)
    }
    private fun convertExtends(parentskun:Node.Declaration.Class.Parents?):IExtends{
        var types:MutableList<Type> = ArrayList()
        if(parentskun!=null){
            parentskun.elements?.forEach {
                if (it is Node.Declaration.Class.Parent.Type) {
                    for (ikun in it.type.pieces) {
                        types.add(Type(ikun.name.name, false))
                    }
                }
            }
        }
        return Extends(types)
    }
    private fun convertFuncParameters(params: Node.Declaration.Function.Params?):IParameters{
        var parameters:MutableList<Parameter> = ArrayList()
        if(params != null)
        for(param in params.elements){
            if(param.typeRef != null)
            if(param.typeRef!!.type is Node.Type.Simple){
                var typeName=""
                for(ikun in (param.typeRef!!.type as Node.Type.Simple).pieces){
                    typeName=ikun.name.name
                }
                parameters.add(Parameter(Type(typeName,true),param.name!!.name))
            }
        }
        return Parameters(parameters)
    }

/*
    fun visitNamedFunction(kfunc:KtNamedFunction){
        println("m: ${kfunc.name}, parentscope: ${currentScope?.getName()}: ${currentScope?.getType()}")
        var tr:String=""
        if(kfunc.typeReference == null) {
            tr = "void"
        }else if(kfunc.typeReference!!.text==null){
            tr="void"
        }else if(kfunc.typeReference!!.text!!.isEmpty()){
            tr="void"
        }else{
            tr=kfunc.typeReference!!.text!!
        }
        println(tr)
        if(currentScope is ClassDeclaration){
            currentScope=codeBuilder.declareMethod(
                currentScope as ClassDeclaration,
                convertModifiers(kfunc.modifierList),
                Type(tr,true),
                kfunc.name!!,convertMethodParameters(kfunc.valueParameters)
            )

        }
        currentScope!!.setCode(kfunc.text)


        for(elemchild in kfunc.children){
            parse(elemchild)
        }
        currentScope=currentScope!!.getParent()
        currentScope!!.setCode(kfunc.text)
    }
    fun visitPackageDirective(ktPackageDirective: KtPackageDirective){
        (rootScope as? CompilationUnitDeclaration)?.setPackageName(ktPackageDirective.qualifiedName)
        for(elemchild in ktPackageDirective.children){
            parse(elemchild)
        }
    }
    fun visitMethodInvokeExpression(dotQualifiedExpression: KtDotQualifiedExpression){
        var receiverkun:KtExpression=dotQualifiedExpression.receiverExpression
        if(dotQualifiedExpression.selectorExpression is KtCallExpression) {
            var targetkun: KtCallExpression = dotQualifiedExpression.selectorExpression as KtCallExpression
            if(targetkun.referenceExpression() != null) {
                var methodName = targetkun.referenceExpression()!!.text
                println(" --> METHOD: $methodName")
                /*for(i in targetkun.valueArguments){
                    println(" --> ARG: ${i.text}")
                }*/
                var arguments:MutableList<Variable> =convertArguments(targetkun.valueArguments)
                var objName:String?=null
                var isIdCall:Boolean=false
                objName=receiverkun.text
                if(objName!=null){
                    if(objName.equals("System.out")){
                        codeBuilder.invokeStaticMethod(currentScope,Type("System.out"),methodName,true,
                            "",arguments)
                            .setCode(dotQualifiedExpression.text)
                    }else{
                        codeBuilder.invokeMethod(currentScope,objName,methodName,true,
                            "",arguments)
                            .setCode(dotQualifiedExpression.text)
                    }
                }

            }
        }
    }
    fun parse(element: PsiElement){
        for(celem in element.children){
            if(celem is KtClass) {
                visitClass(celem)
            }else if(celem is KtNamedFunction) {
                //visitFunction(celem, 0)
                visitNamedFunction(celem)
            }else if(celem is KtPackageDirective){
                visitPackageDirective(celem)
            }else if(celem is KtDotQualifiedExpression){
                visitMethodInvokeExpression(celem)
            }
        }
    }
    private fun convertExtends(klass: KtClass):IExtends{
        var types:MutableList<Type> = ArrayList()

        if(klass.getSuperTypeList()==null)
            return Extends()
        for(sp in klass.getSuperTypeList()!!.children){
            if(sp is KtSuperTypeEntry){
                types.add(Type(sp.typeReference!!.text,false))
            }
        }
        return Extends(types)
    }
    private fun convertMethodParameters(params: List<KtParameter>):IParameters{
        var parameters:MutableList<Parameter> = ArrayList()
        for(param in params){
            parameters.add(Parameter(Type(param.typeReference!!.text!!,true),param.name!!))
        }
        return Parameters(parameters)
    }
    private fun convertArguments(args:List<KtValueArgument>):MutableList<Variable>{
        var variables:MutableList<Variable> = ArrayList()
        for( arg in args){
            var v:Variable?=null
            var oe:KtExpression?=arg.getArgumentExpression()
            if(oe != null){
                var e:KtExpression=oe!!
                if(e is KtStringTemplateExpression){
                    var textkun:String=""
                    for(elem in e.entries){
                        textkun=elem.text
                    }
                    v=VariableFactory.createConstantVariable(currentScope,Type("kotlin.String",true),"",textkun)
                    println("S")
                }
            }
            if(v == null){
                println("errtype ")
            }else{
                variables.add(v)
            }

        }
        return variables
    }
 */

}