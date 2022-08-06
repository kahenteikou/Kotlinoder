package io.github.kahenteikou.kotlinoder.instrumentation

class CompilationUnitDeclaration_Impl(id:String,parent:Scope?,name:String,packageName: String):
ScopeImpl(id,parent,ScopeType.COMPILATION_UNIT,name,null),CompilationUnitDeclaration{
    

}
final class CompilationUnitMetaData {
    private final var packageName:String
    constructor(packageName:String) {
        this.packageName = packageName
    }
    fun getPackageName():String {
        return packageName
    }
}