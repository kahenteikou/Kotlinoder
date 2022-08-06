package io.github.kahenteikou.kotlinoder.instrumentation

class CompilationUnitDeclaration_Impl {
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