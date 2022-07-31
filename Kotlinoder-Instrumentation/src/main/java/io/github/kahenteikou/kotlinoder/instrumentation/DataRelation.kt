package io.github.kahenteikou.kotlinoder.instrumentation

interface DataRelation {
    var Sender:Invocation
    var Receiver:Invocation
}
class DataRelationImpl:DataRelation{
    
}