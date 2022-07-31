package io.github.kahenteikou.kotlinoder.instrumentation

interface Scope : CodeEntity{
    fun getParent(): Scope
    fun getType(): ScopeType
    fun getName(): String
    fun getScopeArgs(): Array<Object>
    fun getVariables():Collection<Variable>

}