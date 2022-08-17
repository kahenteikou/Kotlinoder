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
            if(splitArg.isEmpty()){
                retStatement=Node.Expression.Call(
                    Node.Expression.Name(entity.getMethodName()!!),
                    null,null,null
                )
            }else{
                lateinit var rootEnum:Node.Expression
                lateinit var callStatement:Node.Expression.Call
                callStatement=Node.Expression.Call(
                    Node.Expression.Name(entity.getMethodName()),
                    null,null,null
                )
                var isFirst=true
                for(arg in splitArg){
                    if(isFirst) {
                        rootEnum = Node.Expression.Name(arg)
                        isFirst = false
                    }
                }
            }
        }
        return retStatement
    }

}