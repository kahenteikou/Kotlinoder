package io.github.kahenteikou.kotlinoder.instrumentation

import ktast.ast.Node

interface CodeEntity : CodeEntity_OLD {
    fun getNode() : Node?
    fun setNode(node:Node?)
}