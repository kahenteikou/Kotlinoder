package io.github.kahenteikou.kotlinoder.instrumentation

interface DataRelation {

    fun getSender():Invocation
    fun getReceiver():Invocation
}
class DataRelationImpl:DataRelation{
    private var Sender:Invocation
    private var Receiver:Invocation
    override fun getSender(): Invocation {
        return Sender
    }

    override fun getReceiver(): Invocation {
        return Receiver
    }
    constructor(sender:Invocation,receiver:Invocation){
        Sender=sender
        Receiver=receiver
    }
}
