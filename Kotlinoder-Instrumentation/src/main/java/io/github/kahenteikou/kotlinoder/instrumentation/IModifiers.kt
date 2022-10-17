package io.github.kahenteikou.kotlinoder.instrumentation

interface IModifiers {
    fun getModifiers():MutableList <Modifier>
    fun delModifier(modifier:Modifier)
    fun addModifier(modifier:Modifier)
    fun replaceModifier(oldModifier:Modifier,newModifier:Modifier):Boolean
}