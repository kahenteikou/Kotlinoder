package io.github.kahenteikou.kotlinoder.instrumentation

interface ControlFlow {
    fun createInstance(id:String,type: IType,varName:String,vararg args:Variable):Invocation
    fun callMethod(id: String,varName:String,mName:String,isVoid:Boolean,retValueName:String,vararg args:Variable):Invocation
    fun callStaticMethod(id:String,type:IType,mName:String,isVoid:Boolean,retValueName:String,vararg args:Variable):Invocation
    fun callScope(scope:Scope):ScopeInvocation
    fun getInvocations():ArrayList<Invocation>
}
class ControlFlowImpl:ControlFlow{
    private final val invocations:ArrayList<Invocation> =ArrayList()
    override fun createInstance(id: String, type: IType, varName: String, vararg args: Variable): Invocation {
        val result:Invocation = InvocationImpl(parent,id,type.getFullClassName(),"<init>",true,false,true,varName,*args)
        getInvocations().add(result)
        return result
    }

    override fun callMethod(
        id: String,
        varName: String,
        mName: String,
        isVoid: Boolean,
        retValueName: String,
        vararg args: Variable
    ): Invocation {
        TODO("Not yet implemented")
    }

    override fun callStaticMethod(
        id: String,
        type: IType,
        mName: String,
        isVoid: Boolean,
        retValueName: String,
        vararg args: Variable
    ): Invocation {
        TODO("Not yet implemented")
    }

    override fun callScope(scope: Scope): ScopeInvocation {
        TODO("Not yet implemented")
    }

    override fun getInvocations(): ArrayList<Invocation> {
        return invocations
    }
    private final var parent:Scope
    constructor(parent:Scope){
        this.parent = parent
    }


}