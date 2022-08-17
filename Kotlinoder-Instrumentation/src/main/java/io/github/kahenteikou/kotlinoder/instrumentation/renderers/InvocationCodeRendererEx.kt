package io.github.kahenteikou.kotlinoder.instrumentation.renderers

import io.github.kahenteikou.kotlinoder.instrumentation.Invocation
import io.github.kahenteikou.kotlinoder.instrumentation.Type
import io.github.kahenteikou.kotlinoder.instrumentation.Variable
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
                lateinit var currentEnum:Node.Expression
                lateinit var callStatement:Node.Expression.Call
                var argskun:MutableList<Node.ValueArg> = createParams(entity.getArguments())
                callStatement=Node.Expression.Call(
                    Node.Expression.Name(entity.getMethodName()),
                    null,Node.ValueArgs(argskun,null),null
                )
                var isFirst=true
                for(arg in splitArg.reversed()){
                    if(isFirst) {
                        currentEnum=Node.Expression.Binary(
                            Node.Expression.Name(arg),
                            Node.Expression.Binary.Operator(Node.Expression.Binary.Operator.Token.DOT),
                            callStatement
                        )
                        isFirst = false
                    }else{
                        currentEnum=Node.Expression.Binary(
                            Node.Expression.Name(arg),
                            Node.Expression.Binary.Operator(Node.Expression.Binary.Operator.Token.DOT),
                            currentEnum
                        )
                    }
                }
                retStatement=currentEnum
            }
        }
        return retStatement
    }
    private fun createParams(params:List<Variable?>):MutableList<Node.ValueArg>{
        var ret:MutableList<Node.ValueArg> = ArrayList()
        for(param in params){
            if(param!=null){
                if(param!!.isConstant()!!){
                    lateinit var expressionkun:Node.Expression
                    if(param!!.getType().equals(Type("kotlin.String"))){
                        expressionkun=Node.Expression.StringTemplate(
                            arrayListOf(
                                Node.Expression.StringTemplate.Entry.Regular(
                                    param!!.getValue()!!.toString()
                                )
                            )
                            ,false
                        )
                    }
                    ret.add(Node.ValueArg(
                        null,false,
                        expressionkun
                    ))
                }else{
                    ret.add(Node.ValueArg(
                        null,false,
                        Node.Expression.Name(param!!.getName()!!)
                    ))
                }
            }
        }
        return ret
    }

}