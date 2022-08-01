package io.github.kahenteikou.kotlinoder.instrumentation

interface ControlFlow {
    fun createInstance(id:String,type: IType,varName:String,vararg args:Variable):Invocation
    fun callMethod(id: String,varName:String,mName:String,isVoid:Boolean,retValueName:String,vararg args:Variable):Invocation
    
}