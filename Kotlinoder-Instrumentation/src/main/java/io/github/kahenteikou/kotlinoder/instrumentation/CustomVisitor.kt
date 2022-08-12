package io.github.kahenteikou.kotlinoder.instrumentation

import ktast.ast.Node

open class CustomVisitor {
    companion object{
        fun visit(v:Node,fn: (v: Node, parent: Node?) -> Unit)=object:CustomVisitor(){
            override fun visit(v: Node, parent: Node?) {
                fn(v,parent)
                super.visit(v,parent)
            }
        }
    }
}