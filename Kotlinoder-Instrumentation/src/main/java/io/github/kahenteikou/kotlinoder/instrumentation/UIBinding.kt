package io.github.kahenteikou.kotlinoder.instrumentation

class UIBinding {
    companion object{
        @JvmStatic
        public val scope:Map<String,MutableList<Scope>> = HashMap()
    }
}