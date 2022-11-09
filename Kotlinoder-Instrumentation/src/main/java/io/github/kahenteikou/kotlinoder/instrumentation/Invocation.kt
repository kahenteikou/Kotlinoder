package io.github.kahenteikou.kotlinoder.instrumentation

import io.github.kahenteikou.kotlinoder.instrumentation.invokes.IInvokeAndStatement

//comp
interface Invocation:CodeEntity, IInvokeAndStatement {
    fun getVariableName():String?
    fun setVariableName(text:String?)
    fun getMethodName():String
    fun setMethodName(text:String?)
    fun getReturnValueName():String
    fun getArguments():List<Variable?>
    fun isConstructor():Boolean
    fun isVoid():Boolean
    fun isScope():Boolean
    fun isStatic():Boolean
}
open class InvocationImpl :Invocation{
    private var _id : String
    private final var varName:String?
    private final var MethodName:String
    private final var returnValueName:String
    private final var arguments:ArrayList<Variable?> = ArrayList()
    private final var Constructor:Boolean
    private final var Void: Boolean
    private var code:String?=null
    private final var parent:Scope
    private var Static:Boolean
    constructor(parent:Scope,id:String,varName:String?,methodName:String,
                Constructor:Boolean,isVoid:Boolean,isStatic:Boolean,
    retValName:String,vararg args:Variable?){
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
        try{
            if(varName != null)
                varkun=this.parent.getVariable(varName)
        }catch (e:Exception){
            //??
        }
        if(!isStatic&&isScope() && varkun == null){
            throw IllegalArgumentException("Variable $varName not found in scope ${this.parent.getName()}")
        }else {
            //val typekun:Type
            var type=Type(varName)
        }
    }
    constructor(parent:Scope,id:String,varName:String?,methodName:String,
                 Constructor:Boolean,isVoid:Boolean,isStatic:Boolean,
                 retValName:String,args:List<Variable?>){
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
        try{
            if(varName != null)
                varkun=this.parent.getVariable(varName)
        }catch (e:Exception){
            //??
        }
        if(!isStatic&&isScope() && varkun == null){
            throw IllegalArgumentException("Variable $varName not found in scope ${this.parent.getName()}")
        }else {
            //val typekun:Type
            var type=Type(varName)
        }
    }


    override fun getVariableName(): String? {
        return varName
    }

    override fun getMethodName(): String {
        return MethodName
    }

    override fun getReturnValueName(): String {
        return returnValueName
    }

    override fun getArguments(): List<Variable?> {
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

    override fun setVariableName(text: String?) {
        this.varName=text
    }
    override fun setMethodName(text: String?) {
        this.MethodName=text!!
    }
    fun setStatic(Statickun:Boolean){
        this.Static=Statickun
    }

    override fun toString(): String {
        var resultStr="[ "
        if(this is ScopeInvocationImpl){
            resultStr+="scopeType: " + this.getScope().getType() + ", "
        }
        resultStr += "constructor=$Constructor, var=$varName, mName=$MethodName, retValName=$returnValueName, args=["
        for(arg in arguments){
            resultStr+=(arg.toString() + ", ")
        }
        resultStr += "]"
        return resultStr
    }

}
class ScopeInvocationImpl: InvocationImpl , ScopeInvocation {
    private final var scope:Scope
    override fun getScope(): Scope {
        return scope
    }
    constructor(scope: Scope) : super(scope,"",null,"scope",false,true,true,"") {
        this.scope = scope
    }

    override fun isScope(): Boolean {
        return true
    }
}