package io.github.kahenteikou.kotlinoder.instrumentation

class MethodDeclaration_Impl(id:String,methodName:String,parent:Scope?,returnType:IType,modifiers:IModifiers?,params:IParameters?) :
    ScopeImpl(id,parent,ScopeType.METHOD,methodName,MethodDeclarationMetaData(returnType,modifiers,params)),MethodDeclaration {
    private final var metadata:MethodDeclarationMetaData
    init{
        metadata=getScopeArgs()[0] as MethodDeclarationMetaData
        createParamVariables()
    }
    private fun createParamVariables(){
        for(p in metadata.getParams()!!.getParamenters()!!){
            createVariable(p.getType(),p.getName())
        }
    }
    override fun getModifiers(): IModifiers {
        return metadata.getModifiers()!!
    }

    override fun getParameters(): IParameters {
        return metadata.getParams()!!
    }

    override fun getParameterAsVariable(p: IParameter): Variable {
        return getVariable(p.getName())!!
    }

    override fun getReturnType(): IType {
        return metadata.getType()!!
    }

}
final class MethodDeclarationMetaData {
    private final var type:IType
    private final var modifiers:IModifiers?
    private final var params:IParameters?
    constructor(type:IType,modifiers: IModifiers?,params:IParameters?){
        this.type = type
        this.modifiers = modifiers
        this.params = params
    }
    fun getType():IType{
        return type
    }
    fun getModifiers():IModifiers?{
        return modifiers
    }
    fun getParams():IParameters?{
        return params
    }
}