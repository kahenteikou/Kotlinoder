package io.github.kahenteikou.kotlinoder.instrumentation

class VariableFactory {
    companion object{
        @JvmStatic
        fun createObjectVariable(scope:Scope?,type:Type,varName:String):Variable{
            return VariableImpl(scope,type,varName,null,false)
        }
    }
}