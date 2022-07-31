package io.github.kahenteikou.kotlinoder.instrumentation

interface Variable {
    val Name: String
    val Type: String
    val Value: Object
    val isStatic:Boolean
    val isConstant:Boolean
    

}