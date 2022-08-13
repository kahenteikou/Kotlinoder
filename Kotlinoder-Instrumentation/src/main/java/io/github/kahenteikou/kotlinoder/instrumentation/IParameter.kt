package io.github.kahenteikou.kotlinoder.instrumentation

interface IParameter {
    /**
     * @return name
     */
    fun getName():String

    /**
     * @return type
     */
    fun getType():IType
}
