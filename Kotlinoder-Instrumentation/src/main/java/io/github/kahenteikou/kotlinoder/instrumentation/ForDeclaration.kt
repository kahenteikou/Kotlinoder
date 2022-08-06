package io.github.kahenteikou.kotlinoder.instrumentation

interface ForDeclaration :Scope{
    fun getVarName():String
    fun getFrom():Int
    fun getTo():Int
    fun getInc():Int
}