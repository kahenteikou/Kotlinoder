package io.github.kahenteikou.kotlinoder.instrumentation

import eu.mihosoft.vrl.workflow.FlowFactory
import eu.mihosoft.vrl.workflow.IdGenerator
import org.jetbrains.kotlin.psi.KtVisitor
import java.util.*

class KotlinVisualizationTransformation {
}
class KotlinCodeVisitor : KtVisitor<Unit, VisualCodeBuilder_Impl> {
    private var codeBuilder:VisualCodeBuilder_Impl
    private var rootScope:Scope?=null
    private var currentScope:Scope?=null
    private var lastMethod:Invocation?=null
    private var vIdStack: Stack<String> = Stack()
    private var generator:IdGenerator=FlowFactory.newIdGenerator()
    constructor(codeBuilder:VisualCodeBuilder_Impl){

        this.codeBuilder=codeBuilder
        codeBuilder.setIdRequest(object : IdRequest{
            override fun request(): String {
                return requestId()
            }

        })
    }
    private fun requestId():String{
        var result:String=""
        if(vIdStack.isEmpty()){
            result=vIdStack.pop()
            if(generator.ids.contains(result)){
                error(">> requestId(): id already exists: $result")
                result=generator.newId()
            }else{
                generator.addId(result)
                println(">> USING ID: $result")
            }
        }else{
            result=generator.newId()
        }
        return result
    }
}