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
        if(!entity.getReturnType().equals(Type("void"))) {
            var TypeNameLs:MutableList<Node.Type.Simple.Piece> = ArrayList()
            entity.getReturnType().getFullClassName()?.split(".")?.forEach {it2->
                TypeNameLs.add(
                    Node.Type.Simple.Piece(
                        Node.Expression.Name(it2),
                        null))
            }
            typeref = Node.TypeRef(null,null,null,null,
            Node.Type.Simple(TypeNameLs),
            null,null)
        }
        paramItems=createParams(entity.getParameters())
        if(_invocationRenderer!=null){
            for(R in entity.getControlFlow().getCallObjects()){
                if(R is Invocation){
                    methodItems.add(_invocationRenderer!!.render(R))
                }
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
            if(it == Modifier.STATIC){
                retList.add(Node.Modifier.AnnotationSet(
                    Node.Keyword.At(),
                    null,null,null,
                    arrayListOf(Node.Modifier.AnnotationSet.Annotation(
                        Node.Type.Simple(
                            arrayListOf(
                                Node.Type.Simple.Piece(
                                    Node.Expression.Name("JvmStatic")
                                    ,
                                    null
                                )
                            )
                        ),null
                    )),
                    null


                ))
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
            var DefValue: Node.Expression?=null
            var equalkun:Node.Keyword.Equal?=null
            paramType.getFullClassName()?.split(".")?.forEach {it2->
                TypeNameLs.add(
                    Node.Type.Simple.Piece(
                    Node.Expression.Name(it2),
                    null))
            }
            if(it.getDefaultValue()!=null){
                var defV=it.getDefaultValue()!!
                if(paramType.getFullClassName()=="Int"){
                    DefValue=Node.Expression.Constant(defV.getValue().toString(),
                    Node.Expression.Constant.Form.INT)
                    equalkun=Node.Keyword.Equal()
                }
            }
            retList.add(Node.Declaration.Function.Param(
                null,null,
                Node.Expression.Name(paramName),
                Node.TypeRef(
                    null,null,null,null,
                    Node.Type.Simple(TypeNameLs),
                    null,null
                ),
                equalkun,DefValue
            ))


        }
        return retList
    }


}