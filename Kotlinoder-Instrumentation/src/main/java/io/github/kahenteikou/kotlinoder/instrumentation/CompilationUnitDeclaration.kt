package io.github.kahenteikou.kotlinoder.instrumentation
//complete
interface CompilationUnitDeclaration: Scope {
    fun getFileName():String?
    fun getDeclaredClasses():MutableList<ClassDeclaration>
    fun getPackageName():String?
}