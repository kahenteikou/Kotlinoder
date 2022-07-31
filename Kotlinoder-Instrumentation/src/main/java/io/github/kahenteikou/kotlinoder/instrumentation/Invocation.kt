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
    private var code:String?=null
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
        return arguments
    }

    override fun isConstructor(): Boolean {
        return Constructor
    }

    override fun isVoid(): Boolean {
        return Void
    }

    override fun isScope(): Boolean {
        return false
    }

    override fun isStatic(): Boolean {
        return Static
    }

    override fun getId(): String {
        return _id
    }

    override fun setId(id: String) {
        this._id=id
    }

    override fun getCode(): String? {
        return code
    }

    override fun setCode(code: String?) {
        this.code=code
    }

}