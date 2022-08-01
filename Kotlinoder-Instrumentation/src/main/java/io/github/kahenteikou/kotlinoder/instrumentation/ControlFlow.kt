package io.github.kahenteikou.kotlinoder.instrumentation

interface ControlFlow {
    fun createInstance(id:String,type: IType,varName:String,vararg args:Variable)
    
}