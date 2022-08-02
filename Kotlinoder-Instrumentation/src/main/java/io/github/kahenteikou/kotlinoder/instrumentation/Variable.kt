package io.github.kahenteikou.kotlinoder.instrumentation

import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

interface Variable {
    fun getName(): String?
    fun getType(): IType
    fun getValue(): Object?
    fun setValue(o:Object?)
    fun isStatic():Boolean?
    fun isConstant():Boolean?
    fun setConstant(b:Boolean?)
    fun getScope():Scope


}
class VariableImpl : Variable{
    private final var scope:Scope?
    private final var type:IType
    private final var varName:String?
    private var value:Object?=null
    private var constant:Boolean?=null
    private var staticVar:Boolean?=null
    constructor(scope:Scope?,type:IType,varName:String,value:Object?,constant:Boolean){
        this.scope=scope
        this.type=type
        this.varName=varName
        this.value=value
        this.constant=constant
    }
    constructor(scope:Scope?,type:IType){
        this.scope=scope
        this.type=type
        this.varName=type.getFullClassName()
        this.staticVar=true

    }
    companion object {
        @JvmStatic
        fun createStaticVar(scope: Scope?, type: IType): VariableImpl {
            return VariableImpl(scope, type)
        }
    }

    override fun getName(): String? {
        return varName
    }

    override fun getType(): IType {
        return type
    }

    override fun getValue(): Object? {
        return value
    }

    override fun setValue(o: Object?) {
        this.value=o
    }

    override fun isStatic(): Boolean? {
        return staticVar
    }

    override fun isConstant(): Boolean? {
        return constant
    }

    override fun setConstant(b: Boolean?) {
        this.constant=b
    }

    override fun getScope(): Scope {
        return scope
    }

    override fun hashCode(): Int {
        var hash=5
        hash=hash*83+java.util.Objects.hashCode(this.scope)
        hash=hash*83+java.util.Objects.hashCode(this.varName)
        return hash
    }

    override fun equals(other: Any?): Boolean {

        if(other==null){
            return false
        }
        if(this.javaClass != other.javaClass){
            return false
        }
        var other:VariableImpl=other as VariableImpl
        if(!java.util.Objects.equals(this.scope,other.scope)){
            return false
        }
        if(!java.util.Objects.equals(this.varName,other.varName)){
            return false
        }
        return true
    }

    override fun toString(): String {
        return "[ const=$constant, type=$type, name=$varName, val=$value]"
    }
}