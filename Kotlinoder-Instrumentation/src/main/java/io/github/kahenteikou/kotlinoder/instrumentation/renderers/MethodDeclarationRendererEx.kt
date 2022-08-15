package io.github.kahenteikou.kotlinoder.instrumentation.renderers

import io.github.kahenteikou.kotlinoder.instrumentation.IModifiers
import io.github.kahenteikou.kotlinoder.instrumentation.Invocation
import io.github.kahenteikou.kotlinoder.instrumentation.MethodDeclaration
import io.github.kahenteikou.kotlinoder.instrumentation.Modifier
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
        var modsLskun=createModifiers(entity.getModifiers())
        if(!modsLskun.isEmpty()){
            mods=Node.Modifiers(modsLskun)
        }
        body=Node.Expression.Block(methodItems)
        if(paramItems.isEmpty()) {
            params = Node.Declaration.Function.Params(paramItems, null)
        }else{
            params = Node.Declaration.Function.Params(paramItems, Node.Keyword.Comma())
        }

        retFunc=Node.Declaration.Function(mods,Node.Keyword.Fun(),typeparams,receiverRef,name,params,typeref,postMods,equals,body)

        return retFunc
    }
    private fun createModifiers(imods:IModifiers):List<Node.Modifier>{
        var retList:MutableList<Node.Modifier> = ArrayList()
        imods.getModifiers().forEach {
            //retList.add(Node.Modifier(it))
            if(it == Modifier.PUBLIC){
                retList.add(Node.Modifier.Keyword(Node.Modifier.Keyword.Token.PUBLIC))
            }
            if(it == Modifier.PROTECTED){
                retList.add(Node.Modifier.Keyword(Node.Modifier.Keyword.Token.PROTECTED))
            }
            if(it == Modifier.ABSTRACT){
                retList.add(Node.Modifier.Keyword(Node.Modifier.Keyword.Token.ABSTRACT))
            }
            if(it == Modifier.PRIVATE){
                retList.add(Node.Modifier.Keyword(Node.Modifier.Keyword.Token.PRIVATE))
            }
            if(it == Modifier.FINAL){
                retList.add(Node.Modifier.Keyword(Node.Modifier.Keyword.Token.FINAL))
            }
        }
        return retList
    }


}