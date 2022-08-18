package io.github.kahenteikou.kotlinoder.instrumentation.invokes

interface IInvokeAndStatement {
    companion object {
        @JvmStatic
        fun createObject(id: String, paramLs: Map<String, Any?>): IInvokeAndStatement? =null
    }
}