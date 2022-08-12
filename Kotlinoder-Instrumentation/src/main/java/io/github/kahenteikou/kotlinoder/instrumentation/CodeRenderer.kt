package io.github.kahenteikou.kotlinoder.instrumentation

import ktast.ast.Node

//complete
interface CodeRenderer2 <T : CodeEntity> {
    fun render(entity: T): Node
    fun render(entity:T,nd:Node)
}
interface CodeRenderer <T : CodeEntity> {
    fun render(entity: T): String
    fun render(entity:T,cb:CodeBuilder)
}