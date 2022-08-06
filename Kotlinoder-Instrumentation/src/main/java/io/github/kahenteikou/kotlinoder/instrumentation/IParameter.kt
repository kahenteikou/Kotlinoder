package io.github.kahenteikou.kotlinoder.instrumentation

interface IParameter {
    /**
     * name
     */
    fun getName():String

    /**
     * type
     */
    fun getType():IType
}
