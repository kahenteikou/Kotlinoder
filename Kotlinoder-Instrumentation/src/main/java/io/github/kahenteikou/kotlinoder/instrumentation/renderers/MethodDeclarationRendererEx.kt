package io.github.kahenteikou.kotlinoder.instrumentation.renderers

import io.github.kahenteikou.kotlinoder.instrumentation.Invocation
import io.github.kahenteikou.kotlinoder.instrumentation.MethodDeclaration
import ktast.ast.Node
class MethodDeclarationRendererEx :CodeRendererEx<MethodDeclaration, Node.Declaration.Function>{
    private var _invocationRenderer:CodeRendererEx<Invocation,Node.Statement >?=null
    var invocationRenderer:CodeRendererEx<Invocation,Node.Statement >
        get() = _invocationRenderer!!
        set(value) {
            _invocationRenderer = value
        }
    constructor(){

    }
    constructor(_irenderer:CodeRendererEx<Invocation,Node.Statement>){
        _invocationRenderer = _irenderer
    }

    override fun render(entity: MethodDeclaration): Node.Declaration.Function {
        TODO("Not yet implemented")
    }


}