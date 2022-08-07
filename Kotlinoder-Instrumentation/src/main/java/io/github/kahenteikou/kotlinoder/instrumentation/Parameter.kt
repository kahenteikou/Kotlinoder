package io.github.kahenteikou.kotlinoder.instrumentation

import io.github.kahenteikou.kotlinoder.lang.VLangUtils

class Parameter : IParameter {
    private final var type:Type
    private final var name:String
    constructor(type:Type,name:String){
        this.type = type
        this.name = name
    }
    private fun validate(){
        if(!VLangUtils.isVariableNameValid(name)){
            throw IllegalArgumentException("Invalid variable name: $name")
        }
    }
    override fun getType(): Type {
        return type
    }

    override fun getName(): String {
        return name
    }
}