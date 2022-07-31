package io.github.kahenteikou.kotlinoder.instrumentation

interface DataRelation {

    var Sender:Invocation
    var Receiver:Invocation
}
class DataRelationImpl:DataRelation{
    private lateinit var _Sender:Invocation
    private lateinit var _Receiver:Invocation
    constructor(sender:Invocation,receiver:Invocation){
        _Sender=sender
        _Receiver=receiver
    }

    override var Sender: Invocation
        get() = _Sender
        set(value) {_Sender=value}
    override var Receiver: Invocation
        get() = _Receiver
        set(value) {_Receiver=value}

}