package io.github.kahenteikou.kotlinoder.instrumentation

interface DataRelation {
    var Sender:Invocation
    var Receiver:Invocation
}
class DataRelationImpl:DataRelation{
    private lateinit var _sender : Invocation
    private lateinit var _receiver : Invocation
    constructor(sender: Invocation, receiver: Invocation){
        _sender = sender
        _receiver = receiver
    }
}