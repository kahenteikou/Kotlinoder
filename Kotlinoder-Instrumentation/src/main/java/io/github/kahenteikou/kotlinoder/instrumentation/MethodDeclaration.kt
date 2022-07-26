package io.github.kahenteikou.kotlinoder.instrumentation
//complete

interface MethodDeclaration :Scope {
    fun getModifiers():IModifiers
    fun getParameters():IParameters
    fun getParameterAsVariable(p: IParameter) : Variable
    fun getReturnType(): IType
}