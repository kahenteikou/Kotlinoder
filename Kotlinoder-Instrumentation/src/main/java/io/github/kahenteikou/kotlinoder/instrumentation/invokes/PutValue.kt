package io.github.kahenteikou.kotlinoder.instrumentation.invokes

import io.github.kahenteikou.kotlinoder.instrumentation.Scope
import io.github.kahenteikou.kotlinoder.instrumentation.Variable

interface PutValue  {
    fun getSrcVariable():Variable
    fun getDstVariable():Variable
}