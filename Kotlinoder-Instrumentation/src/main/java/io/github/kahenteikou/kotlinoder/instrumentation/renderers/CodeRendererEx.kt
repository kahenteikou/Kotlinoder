package io.github.kahenteikou.kotlinoder.instrumentation.renderers

import io.github.kahenteikou.kotlinoder.instrumentation.CodeBuilder
import io.github.kahenteikou.kotlinoder.instrumentation.CodeEntity
import ktast.ast.Node

interface CodeRendererEx <T,T2:Node>{
    fun render(entity: T): T2
    //fun render(entity:T,cb: CodeBuilder)
}