package io.github.kahenteikou.kotlinoder.instrumentation

class Parameter_Ext_DefaultValue:IParameter_Ext_DefaultValue {
    private var _defaultValue:Any?
    constructor(defaultValue: Any?){
        _defaultValue=defaultValue
    }
    override fun getValue(): Any? {
        return _defaultValue
    }
}