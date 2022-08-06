package io.github.kahenteikou.kotlinoder.instrumentation

class MethodDeclaration_Impl(id:String,methodName:String,parent:Scope?,returnType:IType,modifiers:IModifiers?,params:IParameters?) :
    ScopeImpl(id,parent,ScopeType.METHOD,methodName,MethodDeclarationMetaData(returnType,modifiers,params)),MethodDeclaration {

}
final class MethodDeclarationMetaData {
    constructor(type:IType,modifiers: IModifiers?,params:IParameters?){
        /*this.type = type
        this.modifiers = modifiers
        this.params = params*/
    }
}