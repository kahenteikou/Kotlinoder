package io.github.kahenteikou.kotlinoder.instrumentation

import ktast.ast.Node

/**
 * Code Entity
 */
interface CodeEntity {
    fun getId() : String
    fun setId(id:String)
    fun getNode() : Node?
    fun setNode(node:Node?)

}