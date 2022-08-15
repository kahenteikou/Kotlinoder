package io.github.kahenteikou.kotlinoder.instrumentation.renderers

import io.github.kahenteikou.kotlinoder.instrumentation.Invocation
import io.github.kahenteikou.kotlinoder.instrumentation.MethodDeclaration
import ktast.ast.Node
class MethodDeclarationRendererEx :CodeRendererEx<MethodDeclaration, Node.Declaration>{
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
        var typeref: Node.TypeRef?=null
        var postMods:MutableList<Node.PostModifier> =ArrayList()
        var equals: Node.Keyword.Equal?=null
        var body: Node.Expression?=null
        var paramItems:MutableList<Node.Declaration.Function.Param> =ArrayList()
        var methodItems:MutableList<Node.Statement> = ArrayList()
        body=Node.Expression.Block(methodItems)
        params=Node.Declaration.Function.Params(paramItems,Node.Keyword.Comma())
        retFunc=Node.Declaration.Function(mods,Node.Keyword.Fun(),typeparams,receiverRef,name,params,typeref,postMods,equals,body)

        return retFunc
    }


}