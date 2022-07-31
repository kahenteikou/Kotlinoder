package io.github.kahenteikou.kotlinoder.instrumentation

interface Invocation:CodeEntity {
    val VariableName:String
    val MethodName:String
    val ReturnValueName:String
    val Arguments:List<Variable>
    val isConstructor:Boolean
    val isVoid:Boolean
    val isScope:Boolean
    val isStatic:Boolean
}