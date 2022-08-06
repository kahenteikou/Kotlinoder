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
}