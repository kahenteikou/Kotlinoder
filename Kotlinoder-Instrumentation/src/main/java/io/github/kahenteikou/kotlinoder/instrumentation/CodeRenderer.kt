package io.github.kahenteikou.kotlinoder.instrumentation

import ktast.ast.Node

interface CodeRenderer <T : CodeEntity> {
    fun render(entity: T): Node
    fun render(entity:T,nd:Node)
}