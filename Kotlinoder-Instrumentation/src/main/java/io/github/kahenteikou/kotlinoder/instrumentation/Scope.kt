package io.github.kahenteikou.kotlinoder.instrumentation

interface Scope : CodeEntity{
    val Parent: Scope
    val Type: ScopeType
    val Name: String
    val ScopeArgs: Array<Object>
    
}