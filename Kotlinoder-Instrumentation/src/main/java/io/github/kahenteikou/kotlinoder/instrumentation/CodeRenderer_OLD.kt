package io.github.kahenteikou.kotlinoder.instrumentation

interface CodeRenderer_OLD <T : CodeEntity_OLD> {
    fun render(entity: T): String
    fun render(entity:T,cb:CodeBuilder)
}