package io.github.kahenteikou.kotlinoder.instrumentation

interface Invocation:CodeEntity {
    fun getVariableName():String
    fun getMethodName():String
    fun getReturnValueName():String
    fun getArguments():List<Variable>
    fun isConstructor():Boolean
    fun isVoid():Boolean
    fun isScope():Boolean
    fun isStatic():Boolean
}