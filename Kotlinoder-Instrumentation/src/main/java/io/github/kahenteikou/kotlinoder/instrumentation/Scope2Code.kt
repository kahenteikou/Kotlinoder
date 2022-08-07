package io.github.kahenteikou.kotlinoder.instrumentation

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
                
            }
        }
    }
    fun renderParams(e:Invocation,cb:CodeBuilder){

    }
}