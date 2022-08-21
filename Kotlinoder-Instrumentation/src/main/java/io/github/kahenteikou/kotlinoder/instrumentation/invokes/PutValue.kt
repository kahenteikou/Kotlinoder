package io.github.kahenteikou.kotlinoder.instrumentation.invokes

import io.github.kahenteikou.kotlinoder.instrumentation.Scope
import io.github.kahenteikou.kotlinoder.instrumentation.Variable

open interface PutValue  :IInvokeAndStatement{
    fun getSrcVariable():Variable
    fun getDstVariable():Variable
}