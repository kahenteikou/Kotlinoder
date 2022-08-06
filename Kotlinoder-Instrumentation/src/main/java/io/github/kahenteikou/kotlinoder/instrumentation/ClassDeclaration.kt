package io.github.kahenteikou.kotlinoder.instrumentation

interface ClassDeclaration : Scope {
    fun getClassModifiers():IModifiers
    fun getClassType():IType
    fun getExtends():IExtends
    fun getImplements():IExtends
    fun getDeclaredMethods():MutableList<MethodDeclaration>
    fun declareMethod(id:String,modifiers:IModifiers,returnType:IType,methodName:String,params:IParameters):MethodDeclaration
}