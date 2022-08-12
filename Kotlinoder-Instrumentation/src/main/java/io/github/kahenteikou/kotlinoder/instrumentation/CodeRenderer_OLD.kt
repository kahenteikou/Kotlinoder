package io.github.kahenteikou.kotlinoder.instrumentation

import ktast.ast.Node

interface CodeRenderer_OLD <T : CodeEntity> {
    fun render(entity: T): String
    fun render(entity:T,cb:CodeBuilder)
}