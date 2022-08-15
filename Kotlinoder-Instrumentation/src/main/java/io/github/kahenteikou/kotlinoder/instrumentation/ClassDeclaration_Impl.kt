package io.github.kahenteikou.kotlinoder.instrumentation
//complete
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
        return metadata.getType()
    }

    override fun getExtends(): IExtends {
        return metadata.getExtends()!!
    }

    override fun getImplements(): IExtends {
        return metadata.getImplements()!!
    }

    override fun getDeclaredMethods(): MutableList<MethodDeclaration> {
        return metadata.getDeclaredMethods()!!
    }
    override fun getStaticDeclaredMethods(): MutableList<MethodDeclaration> {
        return metadata.getStaticDeclaredMethods()!!
    }

    override fun declareMethod(
        id: String,
        modifiers: IModifiers,
        returnType: IType,
        methodName: String,
        params: IParameters
    ): MethodDeclaration {
        if(this.getType() != ScopeType.CLASS &&
                this.getType() != ScopeType.NONE &&
                    this.getType() != ScopeType.COMPILATION_UNIT){
            throw IllegalArgumentException("Specified scopetype does not support method declaration: " + this.getType())
        }
        var methodScope:MethodDeclaration=MethodDeclaration_Impl(id,methodName,this,returnType,modifiers,params)
        metadata.getDeclaredMethods()!!.add(methodScope)
        return methodScope
    }


}
final class ClassDeclarationMetaData {
    private final var type:IType
    private final var modifiers:IModifiers?
    private final var extends : IExtends?
    private final var implements:IExtends?
    private final var declaredMethods:MutableList<MethodDeclaration> = ArrayList()
    private final var staticdeclaredMethods:MutableList<MethodDeclaration> = ArrayList()
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
    fun getStaticDeclaredMethods():MutableList<MethodDeclaration>{
        return staticdeclaredMethods
    }
}