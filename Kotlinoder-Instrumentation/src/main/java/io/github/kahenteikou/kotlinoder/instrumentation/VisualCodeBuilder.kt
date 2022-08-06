package io.github.kahenteikou.kotlinoder.instrumentation

interface VisualCodeBuilder {
    fun declareCompilationUnit(name:String,packageName:String)
    fun assignConstant(scope:Scope?,varName:String,constant:Any?)
    fun assignVariable(scope:Scope?,varNameDest:String,varNameSrc:String)
    fun createInstance(scope:Scope?,type:IType,varName:String,vararg args:Variable?)
    fun createVariable(scope:Scope?,type:IType,varName:String):Variable
    
}