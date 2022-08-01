package io.github.kahenteikou.kotlinoder.instrumentation

interface ControlFlow {
    fun createInstance(id:String,type: IType,varName:String,vararg args:Variable):Invocation
    fun callMethod(id: String,varName:String,mName:String,isVoid:Boolean,retValueName:String,vararg args:Variable):Invocation
    fun callStaticMethod(id:String,type:IType,mName:String,isVoid:Boolean,retValueName:String,vararg args:Variable):Invocation
    fun callScope(scope:Scope):ScopeInvocation
    fun getInvocations():List<Invocation>
}
class ControlFlowImpl:ControlFlow{
    private final val invocations:List<Invocation> =ArrayList()
    override fun getInvocations(): List<Invocation> {
        return invocations
    }
    private final var parent:Scope
    constructor(parent:Scope){
        this.parent = parent
    }
    

}