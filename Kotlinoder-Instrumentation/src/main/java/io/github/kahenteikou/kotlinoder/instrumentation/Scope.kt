package io.github.kahenteikou.kotlinoder.instrumentation

interface Scope : CodeEntity{
    val Parent: Scope

}