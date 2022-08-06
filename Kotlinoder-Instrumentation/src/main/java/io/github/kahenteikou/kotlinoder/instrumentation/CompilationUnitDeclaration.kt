package io.github.kahenteikou.kotlinoder.instrumentation

interface CompilationUnitDeclaration: Scope {
    fun getFileName():String
    fun getDeclaredClasses():MutableList<ClassDeclaration>
    fun getPackageName():String
}