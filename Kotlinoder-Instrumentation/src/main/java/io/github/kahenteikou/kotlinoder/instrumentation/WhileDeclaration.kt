package io.github.kahenteikou.kotlinoder.instrumentation

interface WhileDeclaration : Scope {
    fun getCheck():Invocation
}