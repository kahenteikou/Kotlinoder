package io.github.kahenteikou.kotlinoder.instrumentation

import io.github.kahenteikou.kotlinoder.lang.VLangUtils
import org.jetbrains.kotlin.diagnostics.rendering.renderParameter

class Scope2Code {
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
        cb.append(entity.getReturnType()!!.getFullClassName()!!)
        cb.append(" ").append(entity.getName()).append("(")
        renderParams(entity,cb)
        cb.append(") {").newLine()
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
            cb.append(v.getType().getFullClassName()!!).append(" ").append(v.getName()!!)
        }
    }

}
class ClassDeclarationRenderer :CodeRenderer<ClassDeclaration>{
    
}