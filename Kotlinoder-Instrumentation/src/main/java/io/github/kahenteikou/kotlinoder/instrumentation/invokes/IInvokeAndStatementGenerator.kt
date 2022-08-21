package io.github.kahenteikou.kotlinoder.instrumentation.invokes

interface IInvokeAndStatementGenerator {
    fun createObject(id: String, paramLs: Map<String, Any?>): IInvokeAndStatement
}