package io.github.kahenteikou.kotlinoder.instrumentation

import io.github.kahenteikou.kotlinoder.lang.VLangUtils

class CompilationUnitDeclaration_Impl(id:String,parent:Scope?,name:String,packageName: String):
ScopeImpl(id,parent,ScopeType.COMPILATION_UNIT,name,null),CompilationUnitDeclaration{
    private var metadata:CompilationUnitMetaData
    init{
        if(!VLangUtils.isPackageNameValid(packageName)){
            throw IllegalArgumentException("Invalid package name: $packageName")
        }
        metadata= CompilationUnitMetaData(packageName)
    }

    override fun getFileName(): String {
        return super.getName()
    }

    override fun getDeclaredClasses(): MutableList<ClassDeclaration> {
        TODO("Not yet implemented")
    }

    override fun getPackageName(): String {
        return metadata.getPackageName()
    }

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