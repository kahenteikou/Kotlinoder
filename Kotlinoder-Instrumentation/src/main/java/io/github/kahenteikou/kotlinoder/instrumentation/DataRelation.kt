package io.github.kahenteikou.kotlinoder.instrumentation

interface DataRelation {

    fun getSender():Invocation
    fun getReceiver():Invocation
}
class DataRelationImpl:DataRelation{
}
