package io.github.kahenteikou.kotlinoder.instrumentation

interface ControlFlow {
    fun createInstance(id:String,type: IType,varName:String,vararg args:Variable?):Invocation
    fun callMethod(id: String,varName:String,mName:String,isVoid:Boolean,retValueName:String,vararg args:Variable):Invocation
    fun callStaticMethod(id:String,type:IType,mName:String,isVoid:Boolean,retValueName:String,vararg args:Variable):Invocation
    fun callStaticMethod(id:String,mName:String,isVoid:Boolean,retValueName:String,vararg args:Variable):Invocation

    fun callScope(scope:Scope):ScopeInvocation
    fun getInvocations():MutableList <Invocation>
}
class ControlFlowImpl:ControlFlow{
    private final val invocations:MutableList <Invocation> =java.util.ArrayList<Invocation>()
    override fun createInstance(id: String, type: IType, varName: String, vararg args: Variable?): Invocation {
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
        val result:Invocation= InvocationImpl(parent,id,varName,mName,false,isVoid,false,retValueName,*args)
        getInvocations().add(result)
        return result
    }


    override fun callStaticMethod(
        id: String,
        mName: String,
        isVoid: Boolean,
        retValueName: String,
        vararg args: Variable
    ): Invocation {
        val result:Invocation= InvocationImpl(parent,id,"",mName,false,isVoid,true,retValueName,*args)
        getInvocations().add(result)
        return result
    }
    override fun callStaticMethod(
        id: String,
        type: IType,
        mName: String,
        isVoid: Boolean,
        retValueName: String,
        vararg args: Variable
    ): Invocation {
        val result:Invocation= InvocationImpl(parent,id,type.getFullClassName(),mName,false,isVoid,true,retValueName,*args)
        getInvocations().add(result)
        return result
    }

    override fun callScope(scope: Scope): ScopeInvocation {
        val result:ScopeInvocation = ScopeInvocationImpl(scope)
        getInvocations().add(result)
        return result
    }

    override fun getInvocations(): MutableList <Invocation> {
        return invocations
    }
    private final var parent:Scope
    constructor(parent:Scope){
        this.parent = parent
    }

    override fun toString(): String {
        var result:String = "[\n"
        for(invocation in getInvocations()){
            result += invocation.toString()+"\n"
        }
        result += "]"
        return result
    }


}