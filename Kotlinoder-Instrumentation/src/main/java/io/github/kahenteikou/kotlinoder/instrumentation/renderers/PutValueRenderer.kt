package io.github.kahenteikou.kotlinoder.instrumentation.renderers

import io.github.kahenteikou.kotlinoder.instrumentation.invokes.PutValue
import ktast.ast.Node

open class PutValueRenderer:CodeRendererEx<PutValue, Node.Statement> {
    override fun render(entity: PutValue): Node.Statement {
        TODO("Not yet implemented")
    }

}