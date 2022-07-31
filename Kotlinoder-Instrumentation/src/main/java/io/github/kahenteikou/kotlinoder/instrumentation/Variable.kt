package io.github.kahenteikou.kotlinoder.instrumentation

interface Variable {
    val Name: String
    val Type: String
    var Value: Object
    val isStatic:Boolean
    var isConstant:Boolean
    val Scope:Scope


}