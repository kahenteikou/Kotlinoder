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
    
}