package io.github.kahenteikou.kotlinoder.instrumentation

interface ScopeInvocation : Invocation {
    fun getScope(): Scope
}