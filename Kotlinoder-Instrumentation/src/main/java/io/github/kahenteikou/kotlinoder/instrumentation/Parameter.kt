package io.github.kahenteikou.kotlinoder.instrumentation

import io.github.kahenteikou.kotlinoder.lang.VLangUtils
//complete
class Parameter : IParameter {
    private final var type:Type
    private final var name:String
    private final var defaultValue:IParameter_Ext_DefaultValue?
    constructor(type:Type,name:String,defaultValue:IParameter_Ext_DefaultValue?=null){
        this.type = type
        this.name = name
        this.defaultValue=defaultValue
    }
    private fun validate(){
        if(!VLangUtils.isVariableNameValid(name)){
            throw IllegalArgumentException("Invalid variable name: $name")
        }
    }
    override fun getType(): Type {
        return type
    }

    override fun getDefaultValue(): IParameter_Ext_DefaultValue? {
        return defaultValue
    }

    override fun getName(): String {
        return name
    }
}