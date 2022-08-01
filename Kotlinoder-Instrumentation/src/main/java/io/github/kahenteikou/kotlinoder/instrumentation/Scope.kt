package io.github.kahenteikou.kotlinoder.instrumentation

interface Scope : CodeEntity{
    fun getParent(): Scope
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
    fun getScopes():ArrayList<Scope>
    fun getScopeById(id:String) :Scope
    fun createVariable(type:IType):Variable?
    fun getDataFlow():DataFlow
    fun generateDataFlow()
    fun createScope(id:String,type:ScopeType,name:String,args:Array<Object>):Scope


}
class ScopeImpl:Scope{
    private var _id:String
    private var _parent:Scope
    private var _type:ScopeType
    private final var name:String
    private var scopeArgs:Array<Object>
    
}