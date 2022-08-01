package io.github.kahenteikou.kotlinoder.instrumentation

interface DataFlow {
    fun getRelations():ArrayList<DataRelation>
    fun getRelationsForSender(invocation: Invocation):ArrayList<DataRelation>
    fun getRelationsForReceiver(invocation: Invocation):ArrayList<DataRelation>
    fun create(controlFlow: ControlFlow)
    
}