package io.github.kahenteikou.kotlinoder.instrumentation
//complete
interface CodeRenderer <T : CodeEntity> {
    fun render(entity: T): String
    fun render(entity:T,cb:CodeBuilder)
}