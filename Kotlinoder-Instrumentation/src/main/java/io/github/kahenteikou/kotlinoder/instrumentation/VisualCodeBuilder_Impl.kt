package io.github.kahenteikou.kotlinoder.instrumentation

import eu.mihosoft.vrl.workflow.FlowFactory
import java.util.*
import eu.mihosoft.vrl.workflow.IdGenerator

class VisualCodeBuilder_Impl : VisualCodeBuilder {
    private final val variables: Stack<String> = Stack()
    private var idRequest:IdRequest= object : IdRequest {
        val generator: IdGenerator = FlowFactory.newIdGenerator()
        override fun request(): String {
            return generator.newId()
        }
    }
    private fun popVariable():String{
        return variables.pop()
    }
    private fun createScope(parent:Scope?,type:ScopeType,name:String,vararg args:Any?):Scope{
        if(parent!=null){
            return parent.createScope(idRequest.request(),type,name,*args)
        }else{
            return ScopeImpl(idRequest.request(),null,type,name,*args)
        }
    }
}