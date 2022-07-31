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
class InvocationImpl :Invocation{
    private var _id : String
    private final var varName:String
    private final var MethodName:String
    private final var returnValueName:String
    private final var arguments:List<Variable> = ArrayList()
    private final var Constructor:Boolean
    private final var Void: Boolean
    private var code:String
    private final var parent:Scope
    private var Static:Boolean
    constructor(parent:Scope,id:String,varName:String,methodName:String,
                Constructor:Boolean,isVoid:Boolean,isStatic:Boolean,
    retValName:String,vararg args:Variable){
        
    }
}