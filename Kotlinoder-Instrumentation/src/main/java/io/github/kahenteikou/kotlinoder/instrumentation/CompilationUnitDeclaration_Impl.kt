package io.github.kahenteikou.kotlinoder.instrumentation
//complete
import io.github.kahenteikou.kotlinoder.lang.VLangUtils
import java.util.stream.Collectors

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
        return getScopes().stream().
                filter{it is ClassDeclaration}.
                map{it->it as ClassDeclaration}.
                collect(Collectors.toList())
    }

    override fun getPackageName(): String {
        return metadata.getPackageName()
    }

    override fun setPackageName(packageName: String) {
        metadata.setPackageName(packageName)
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
    fun setPackageName(packageName:String) {
        this.packageName = packageName
    }
}