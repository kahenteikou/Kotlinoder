package io.github.kahenteikou.kotlinoder.instrumentation

class ClassDeclaration_Impl(id:String,parent:Scope?,type:IType,modifiers: IModifiers?,extends: IExtends?,implements: IExtends?) :
ScopeImpl(id,parent,ScopeType.CLASS,type.getFullClassName()!!,
    ClassDeclarationMetaData(type,modifiers,extends,implements)),
ClassDeclaration{
    private final var metadata:ClassDeclarationMetaData
    init{
        metadata=getScopeArgs()[0] as ClassDeclarationMetaData
        createVariable(getClassType(),"this")
    }

    override fun getClassModifiers(): IModifiers {
        return metadata.getModifiers()!!
    }

    override fun getClassType(): IType {
        TODO("Not yet implemented")
    }

    override fun getExtends(): IExtends {
        TODO("Not yet implemented")
    }

    override fun getImplements(): IExtends {
        TODO("Not yet implemented")
    }

    override fun getDeclaredMethods(): MutableList<MethodDeclaration> {
        TODO("Not yet implemented")
    }

    override fun declareMethod(
        id: String,
        modifiers: IModifiers,
        returnType: IType,
        methodName: String,
        params: IParameters
    ): MethodDeclaration {
        TODO("Not yet implemented")
    }


}
final class ClassDeclarationMetaData {
    private final var type:IType
    private final var modifiers:IModifiers?
    private final var extends : IExtends?
    private final var implements:IExtends?
    private final var declaredMethods:MutableList<MethodDeclaration> = ArrayList()
    constructor(type:IType,modifiers:IModifiers?,extends:IExtends?,implements:IExtends?) {
        this.type = type
        this.modifiers = modifiers
        this.extends = extends
        this.implements = implements
    }
    fun getExtends():IExtends?{
        return extends
    }
    fun getImplements():IExtends?{
        return implements
    }
    fun getType():IType{
        return type
    }
    fun getModifiers():IModifiers?{
        return modifiers
    }
    fun getDeclaredMethods():MutableList<MethodDeclaration>{
        return declaredMethods
    }
}