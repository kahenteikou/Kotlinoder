package io.github.kahenteikou.kotlinoder.instrumentation

import java.util.*
import kotlin.collections.ArrayList
//complete

class Parameters:IParameters {
    private final var arguments:MutableList<IParameter> = ArrayList()
    private var readOnlyParams:MutableList<IParameter>? = null
    constructor(vararg params:IParameter){
        this.arguments.addAll(params)
    }

    override fun getParamenters(): MutableList<IParameter> {
        if(readOnlyParams==null){
            readOnlyParams = Collections.unmodifiableList(arguments)
        }
        return readOnlyParams!!
    }
}