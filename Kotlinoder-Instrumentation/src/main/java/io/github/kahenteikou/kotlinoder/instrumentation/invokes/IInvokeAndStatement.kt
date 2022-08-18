package io.github.kahenteikou.kotlinoder.instrumentation.invokes

interface IInvokeAndStatement {
    fun createObject(id:String,paramLs:Map<String,Any?>):IInvokeAndStatement
}