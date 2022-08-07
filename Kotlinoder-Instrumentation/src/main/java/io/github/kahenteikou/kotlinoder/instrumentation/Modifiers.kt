package io.github.kahenteikou.kotlinoder.instrumentation

import java.util.*
import kotlin.collections.ArrayList

final class Modifiers :IModifiers{
    private final var modifiers:MutableList<Modifier> = ArrayList()
    private var readOnlyModifiers:List<Modifier>?=null
    constructor(vararg modifiers:Modifier){
        this.modifiers.addAll(modifiers)
    }

    override fun getModifiers(): MutableList<Modifier> {
        if(readOnlyModifiers==null){
            readOnlyModifiers= Collections.unmodifiableList(modifiers)
        }
        return readOnlyModifiers as MutableList<Modifier>
    }

}