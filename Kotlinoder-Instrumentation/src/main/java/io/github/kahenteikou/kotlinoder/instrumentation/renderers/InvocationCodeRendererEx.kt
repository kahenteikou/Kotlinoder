package io.github.kahenteikou.kotlinoder.instrumentation.renderers

import io.github.kahenteikou.kotlinoder.instrumentation.Invocation
import ktast.ast.Node

class InvocationCodeRendererEx:CodeRendererEx<Invocation, Node.Statement> {
    constructor(){

    }
    override fun render(entity: Invocation): Node.Statement {
        lateinit var retStatement:Node.Statement
        if(entity.isConstructor()){

        }else if(!entity.isScope()){
            var splitArg=entity.getVariableName()!!.split(".")
            if(splitArg.size<2){
                
            }
        }
        return retStatement
    }

}