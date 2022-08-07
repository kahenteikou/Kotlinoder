package io.github.kahenteikou.kotlinoder.instrumentation

interface CodeRenderer <T : CodeEntity> {
    fun render(entity: T): String
    //fun render(entity:T,)
}