package io.github.kahenteikou.kotlinoder.instrumentation

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.ListMultimap

interface DataFlow {
    fun getRelations():MutableList<DataRelation>
    fun getRelationsForSender(invocation: Invocation):MutableList<DataRelation>
    fun getRelationsForReceiver(invocation: Invocation):MutableList<DataRelation>
    fun create(controlFlow: ControlFlow)

}
class DataFlowImpl :DataFlow{
    val _relations:ArrayList<DataRelation> = ArrayList()
    val _relationsForSender:ListMultimap<Invocation,DataRelation> = ArrayListMultimap.create()
    val _relationsForReceiver:ListMultimap<Invocation,DataRelation> = ArrayListMultimap.create()
    constructor(sender:Invocation,receiver:Invocation){
        val relation:DataRelationImpl= DataRelationImpl(sender,receiver)
        _relations.add(relation)
        _relationsForSender.put(sender,relation)
        println("sender:"+_relationsForSender.get(sender).size)
        _relationsForReceiver.put(receiver,relation)
    }
    fun createDataRelation(sender:Invocation,receiver:Invocation){
        var relation=DataRelationImpl(sender,receiver)
        _relations.add(relation)
        _relationsForSender.put(sender,relation)
        println("sender: " + _relationsForSender.get(sender).size)
        _relationsForReceiver.put(receiver,relation)
    }
    override fun getRelations(): MutableList<DataRelation> {
        return _relations
    }

    override fun getRelationsForSender(invocation: Invocation): MutableList<DataRelation> {
        return _relationsForSender.get(invocation) as MutableList<DataRelation>

    }

    override fun getRelationsForReceiver(invocation: Invocation): MutableList<DataRelation> {
        return _relationsForReceiver.get(invocation) as MutableList<DataRelation>
    }

    override fun create(controlFlow: ControlFlow) {
        println(">> creating dataflow: ")
        var senders:HashMap<String,Invocation> = HashMap()
        for(i : Invocation in controlFlow.getInvocations()){
            println(" --> i:" + i.getMethodName())
            if(!i.isVoid()){
                println("  |--> potential sender with var " + i.getReturnValueName())
                senders.put(i.getReturnValueName(),i)
            }
        }
        for(receiver:Invocation in controlFlow.getInvocations()){
            for(v:Variable in receiver.getArguments()){
                var sender=senders.get(v.getName())
                println(">> searching sender for " + v.getName()
                 + " with type " + v.getType())
                if(sender!=null){
                    println("--> sender found for '"
                    + v.getName()
                    + "', " + sender.getMethodName())
                    createDataRelation(sender,receiver)
                }
            }
        }
        for(i:Invocation in controlFlow.getInvocations()){
            if(i is ScopeInvocation ){
                var subScope=i.getScope()
                subScope.getDataFlow().create(subScope.getControlFlow())
            }
        }
    }

}