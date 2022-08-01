package io.github.kahenteikou.kotlinoder.instrumentation

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.ListMultimap

interface DataFlow {
    fun getRelations():ArrayList<DataRelation>
    fun getRelationsForSender(invocation: Invocation):ArrayList<DataRelation>
    fun getRelationsForReceiver(invocation: Invocation):ArrayList<DataRelation>
    fun create(controlFlow: ControlFlow)

}
class DataFlowImpl :DataFlow{
    val _relations:ArrayList<DataRelation> = ArrayList()
    val _relationsForSender:ListMultimap<Invocation,DataRelation> = ArrayListMultimap.create()
    val _relationsForReceiver:ListMultimap<Invocation,DataRelation> = ArrayListMultimap.create()
    

    override fun getRelations(): ArrayList<DataRelation> {
        return _relations
    }

    override fun getRelationsForSender(invocation: Invocation): ArrayList<DataRelation> {
        TODO("Not yet implemented")
    }

    override fun getRelationsForReceiver(invocation: Invocation): ArrayList<DataRelation> {
        TODO("Not yet implemented")
    }

    override fun create(controlFlow: ControlFlow) {
        TODO("Not yet implemented")
    }

}