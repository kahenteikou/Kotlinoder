package io.github.kahenteikou.kotlinoder.instrumentation

class UIBinding {
    companion object{
        @JvmStatic
        public val scopes:MutableMap<String,MutableList<Scope>> = HashMap()
    }
}