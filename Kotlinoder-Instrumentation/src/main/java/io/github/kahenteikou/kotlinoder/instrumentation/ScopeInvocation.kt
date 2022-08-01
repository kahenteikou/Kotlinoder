package io.github.kahenteikou.kotlinoder.instrumentation

interface ScopeInvocation {
    fun getScope(): Scope
}
class ScopeInvocationImpl: ScopeInvocation {
    override fun getScope(): Scope {
        TODO("Not yet implemented")
    }
}