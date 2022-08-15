package io.github.kahenteikou.kotlinoder.instrumentation.renderers

import io.github.kahenteikou.kotlinoder.instrumentation.Invocation
import io.github.kahenteikou.kotlinoder.instrumentation.MethodDeclaration
import ktast.ast.Node
class MethodDeclarationRendererEx :CodeRendererEx<MethodDeclaration, Node.Declaration.Function>{
    private var _invocationRenderer:CodeRendererEx<Invocation,MutableList<Node.Statement>>?=null
    
}