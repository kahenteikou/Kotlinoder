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
    private final var arguments:ArrayList<Variable> = ArrayList()
    private final var Constructor:Boolean
    private final var Void: Boolean
    private var code:String
    private final var parent:Scope
    private var Static:Boolean
    constructor(parent:Scope,id:String,varName:String,methodName:String,
                Constructor:Boolean,isVoid:Boolean,isStatic:Boolean,
    retValName:String,vararg args:Variable){
        this.parent=parent
        this._id=id
        this.varName=varName
        this.MethodName=methodName
        this.Constructor=Constructor
        this.Void=isVoid
        this.Static=isStatic
        this.returnValueName=retValName
        arguments.addAll(args)
        var varkun:Variable?=null
    }

    override fun getVariableName(): String {
        return varName
    }

    override fun getMethodName(): String {
        return MethodName
    }

    override fun getReturnValueName(): String {
        return returnValueName
    }

    override fun getArguments(): List<Variable> {
        TODO("Not yet implemented")
    }

    override fun isConstructor(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isVoid(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isScope(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isStatic(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getId(): String {
        TODO("Not yet implemented")
    }

    override fun setId(id: String) {
        TODO("Not yet implemented")
    }

    override fun getCode(): String {
        TODO("Not yet implemented")
    }

    override fun setCode(code: String) {
        TODO("Not yet implemented")
    }

}