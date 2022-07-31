package io.github.kahenteikou.kotlinoder.instrumentation

import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

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
class VariableImpl : Variable{
    private final var scope:Scope
    private final var type:IType
    private final var varName:String
    private var value:JvmType.Object
    private var constant:Boolean
    private var staticVar:Boolean
    constructor(scope:Scope,type:IType,varName:String,value:JvmType.Object,constant:Boolean,staticVar:Boolean){
        this.scope=scope
        this.type=type
        this.varName=varName
        this.value=value
        this.constant=constant
        this.staticVar=staticVar
    }
}