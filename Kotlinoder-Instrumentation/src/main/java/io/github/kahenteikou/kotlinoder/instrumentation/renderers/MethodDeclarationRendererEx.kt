package io.github.kahenteikou.kotlinoder.instrumentation.renderers

import io.github.kahenteikou.kotlinoder.instrumentation.*
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
        paramItems=createParams(entity.getParameters())
        if(_invocationRenderer!=null){
            for(inv in entity.getControlFlow().getInvocations()){
                methodItems.add(_invocationRenderer!!.render(inv))
            }
        }
        body=Node.Expression.Block(methodItems)
        params = Node.Declaration.Function.Params(paramItems, null)

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
    private fun createParams(params: IParameters):MutableList<Node.Declaration.Function.Param>{
        var retList:MutableList<Node.Declaration.Function.Param> = ArrayList()
        params.getParamenters().forEach{
            var paramName=it.getName()
            var paramType=it.getType()
            var TypeNameLs:MutableList<Node.Type.Simple.Piece> = ArrayList()
            paramType.getFullClassName()?.split(".")?.forEach {it2->
                TypeNameLs.add(
                    Node.Type.Simple.Piece(
                    Node.Expression.Name(it2),
                    null))
            }
            retList.add(Node.Declaration.Function.Param(
                null,null,
                Node.Expression.Name(paramName),
                Node.TypeRef(
                    null,null,null,null,
                    Node.Type.Simple(TypeNameLs),
                    null,null
                ),
                null,null
            ))


        }
        return retList
    }


}