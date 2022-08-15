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

    /**
     * @return DefaultValue
     */
    fun getDefaultValue():IParameter_Ext_DefaultValue?
}
interface IParameter_Ext_DefaultValue{
    fun getValue():Any?
}