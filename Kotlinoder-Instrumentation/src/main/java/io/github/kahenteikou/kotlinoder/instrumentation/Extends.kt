package io.github.kahenteikou.kotlinoder.instrumentation

import java.util.*
import kotlin.collections.ArrayList
//complete
class Extends : IExtends {
    private final var types :MutableList<IType> = ArrayList()
    private var readOnlyTypes:List<IType>?=null
    constructor(vararg types:IType){
        this.types.addAll(types)
    }

    override fun getTypes(): MutableList<IType> {
        if(readOnlyTypes==null){
            readOnlyTypes= Collections.unmodifiableList(types)
        }
        return readOnlyTypes as MutableList<IType>
    }
}