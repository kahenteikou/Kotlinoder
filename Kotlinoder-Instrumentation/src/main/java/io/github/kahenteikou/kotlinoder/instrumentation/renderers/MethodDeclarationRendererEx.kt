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
        lateinit var retFunc:Node.Declaration.Function
        var mods:Node.Modifiers?=null
        var typeparams: Node.TypeParams?=null
        var receiverRef: Node.TypeRef?=null
        var name:Node.Expression.Name=Node.Expression.Name(entity.getName())
        var params: Node.Declaration.Function.Params?=null
        
        return retFunc
    }


}