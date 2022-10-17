package io.github.kahenteikou.kotlinoder.instrumentation
//complete
import java.util.*
import kotlin.collections.ArrayList

final class Modifiers :IModifiers{
    private final var modifiers:MutableList<Modifier> = ArrayList()
    private var readOnlyModifiers:List<Modifier>?=null
    constructor(vararg modifiers:Modifier){
        this.modifiers.addAll(modifiers)
    }

    constructor(modifiers: List<Modifier>){
        this.modifiers.addAll(modifiers)
    }

    //mark
    override fun getModifiers(): MutableList<Modifier> {
        if(readOnlyModifiers==null){
            readOnlyModifiers= Collections.unmodifiableList(modifiers)
        }
        return readOnlyModifiers as MutableList<Modifier>
    }

    override fun addModifier(modifier: Modifier) {
        this.modifiers.add(modifier)
    }

    override fun delModifier(modifier: Modifier) {
        if(this.modifiers.contains(modifier))
            this.modifiers.remove(modifier)
    }

    override fun replaceModifier(oldModifier: Modifier, newModifier: Modifier): Boolean {
        if(this.modifiers.contains(oldModifier)){
            this.modifiers.remove(oldModifier)
            this.modifiers.add(newModifier)
            return true
        }else{
            return false
        }
    }


}