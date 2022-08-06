package io.github.kahenteikou.kotlinoder.instrumentation
//complete
class WhileDeclaration_Impl (id:String,parent:Scope?,invocation: Invocation):
ScopeImpl(id,parent,ScopeType.WHILE,ScopeType.WHILE.name,WhileDeclarationMetaData(invocation)) ,
WhileDeclaration {
    private final var metadata:WhileDeclarationMetaData
    init{
        metadata=getScopeArgs()[0] as WhileDeclarationMetaData
    }
    override fun getCheck():Invocation{
        return metadata.getCheck()
    }
}
class WhileDeclarationMetaData {
    private final var check:Invocation
    constructor(check:Invocation){
        this.check = check
    }
    fun getCheck():Invocation{
        return check
    }
}