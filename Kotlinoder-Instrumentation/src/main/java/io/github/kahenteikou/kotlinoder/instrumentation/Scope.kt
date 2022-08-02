package io.github.kahenteikou.kotlinoder.instrumentation

import org.jetbrains.kotlin.fir.resolve.calls.Unsupported
import java.lang.UnsupportedOperationException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

interface Scope : CodeEntity{
    fun getParent(): Scope?
    fun getType(): ScopeType
    fun getName(): String
    fun getScopeArgs(): Array<Object>
    fun getVariables():Collection<Variable>
    fun getVariable(name:String):Variable?
    fun createVariable(type:IType,varName:String):Variable?
    fun createStaticVariable(type:IType):Variable?
    fun assignConstant(varName:String,constant:Object)
    fun assignVariable(varNameDest:String,varNameSrc:String)
    fun getControlFlow():ControlFlow
    fun getScopes():List<Scope>
    fun getScopeById(id:String) :Scope
    fun createVariable(type:IType):Variable?
    fun getDataFlow():DataFlow
    fun generateDataFlow()
    fun createScope(id:String,type:ScopeType,name:String,args:Array<Object>):Scope


}
class ScopeImpl:Scope{
    private var _id:String
    private var _parent:Scope?
    private var _type:ScopeType
    private final var name:String
    private var scopeArgs:Array<Object>
    private var variables:HashMap<String,Variable> = HashMap()
    private var controlFlow:ControlFlow
    private var dataFlow:DataFlow
    private final var scopes:ArrayList<Scope> = ArrayList()
    private var code:String?=null
    private var readOnlyScopes:List<Scope>? = null
    constructor(id:String ,parent:Scope?,type:ScopeType,name:String,args:Array<Object>){
        this._id = id
        this._parent = parent
        this._type = type
        this.name = name
        this.scopeArgs = args
        this.controlFlow = ControlFlowImpl(this)
        this.dataFlow = DataFlowImpl()
        if(parent != null){
            if(this._parent is ScopeImpl){
                (this._parent as ScopeImpl).addScope(this)
            }else{
                throw UnsupportedOperationException("Unsupported parent scope specified." +
                 " Only ScopeImpl based based implementations are supported!")
            }
            if(parent.getType() != ScopeType.CLASS &&
                    parent.getType() != ScopeType.NONE &&
                    parent.getType() != ScopeType.COMPILATION_UNIT){
                parent.getControlFlow().callScope(this)
            }
        }
    }
    override fun getScopeArgs(): Array<Object>{
        return scopeArgs
    }
    private fun addScope(s : ScopeImpl){
        scopes.add(s)
    }

    override fun getType(): ScopeType {
        return _type
    }
    override fun getVariables():Collection<Variable>{
        return variables.values
    }
    override fun getVariable(name:String):Variable?{
        var result=variables.get(name)
        if(result == null && this.getParent() != null){
            result= getParent()?.getVariable(name)
        }
        if(result != null){
            var parentName="<unknown>"
            if(_parent != null){
                parentName= _parent!!.getName()
            }
        }
        return result
    }
    override fun createVariable(type:IType,varName:String):Variable?{
        var __variable:Variable=VariableImpl(this,type,varName,null,false)
        variables.put(varName,__variable)
        return __variable

    }
    override fun createVariable(type:IType):Variable?{
        var varNamePrefix="vrlInternalVar"
        var counter:Int = 0
        var varName:String = varNamePrefix + counter.toString()
        while(getVariable(varName) != null){
            counter++
            varName = varNamePrefix + counter.toString()
        }
        return createVariable(type,varName)

    }
    override fun createStaticVariable(type:IType):Variable?{
        var variablekun=VariableImpl.createStaticVar(_parent,type)
        variables.put(variablekun.getName()!!,variablekun)
        return variablekun
    }
    override fun assignConstant(varName:String,constant:Object){
        var variablekun=getVariable(varName)
        if(variablekun == null){
            throw IllegalArgumentException("Variable $varName does not exist!")
        }else{
            variablekun.setValue(constant)
            variablekun.setConstant(true)
        }
    }
    override fun assignVariable(varNameDest:String,varNameSrc:String){
        var varDest=getVariable(varNameDest)
        var varSrc=getVariable(varNameSrc)
        if(varDest == null) {
            throw IllegalArgumentException("Variable $varNameDest does not exist!")
        }
        if(varSrc == null) {
            throw IllegalArgumentException("Variable $varNameSrc does not exist!")
        }
        println(">> assignment: $varNameDest = $varNameSrc")
    }
    override fun getControlFlow():ControlFlow{
        return controlFlow
    }
    override fun getParent(): Scope?{
        return _parent
    }
    override fun getScopes():List<Scope>{
        if(readOnlyScopes == null){
            readOnlyScopes = Collections.unmodifiableList(scopes)
        }
        return readOnlyScopes!!
    }

    override fun toString(): String {
        var result="Scope:$_type"
        result += "\n>> Variables:\n"
        for(v:Variable in variables.values){
            result += " --> $v \n"
        }
        result += "\n>> ControlFlow:\n $controlFlow"
        result += "\n>> SubScopes:\n"
        for(s:Scope in scopes){
            result += "$s \n"
        }
        return result
    }
    override fun getName(): String{
        return name
    }
    override fun getId() : String{
        return _id
    }
    override fun setId(id:String){
        this._id=id
    }

    override fun getCode() : String?{
        return code
    }
    override fun setCode(code:String?){
        this.code=code
    }
    override fun getDataFlow():DataFlow{
        return dataFlow
    }


}