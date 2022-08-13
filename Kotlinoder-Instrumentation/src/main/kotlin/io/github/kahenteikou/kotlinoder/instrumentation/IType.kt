package io.github.kahenteikou.kotlinoder.instrumentation

interface IType {
    fun getFullClassName() : String?
    fun getPackageName():String?
    fun getShortName(): String?
}