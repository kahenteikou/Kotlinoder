package io.github.kahenteikou.kotlinoder.instrumentation

interface Variable {
    fun getName(): String
    fun getType(): String
    fun getValue(): Object
    fun setValue(o:Object)
    fun isStatic():Boolean
    fun isConstant():Boolean
    fun setConstant(b:Boolean)
    fun getScope():Scope


}