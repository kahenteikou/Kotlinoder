package io.github.kahenteikou.kotlinoder.instrumentation.renderers

import io.github.kahenteikou.kotlinoder.instrumentation.ClassDeclaration
import io.github.kahenteikou.kotlinoder.instrumentation.MethodDeclaration
import io.github.kahenteikou.kotlinoder.instrumentation.Modifier
import io.github.kahenteikou.kotlinoder.instrumentation.Type
import ktast.ast.Node

class ClassDeclarationRendererEx:CodeRendererEx<ClassDeclaration, Node.Declaration.Class> {
    private var _methodDeclarationRenderer:CodeRendererEx<MethodDeclaration,Node.Declaration>?=null
    var methodDeclarationRenderer:CodeRendererEx<MethodDeclaration,Node.Declaration>
        get() = _methodDeclarationRenderer!!
        set(value) {
            _methodDeclarationRenderer = value
        }
    constructor(){

    }
    constructor(coder:CodeRendererEx<MethodDeclaration,Node.Declaration>){
        methodDeclarationRenderer = coder
    }
    override fun render(entity: ClassDeclaration): Node.Declaration.Class {
        lateinit var retClass:Node.Declaration.Class
        var bodykun:Node.Declaration.Class.Body?=null
        var modifiers:Node.Modifiers?=null
        var name: Node.Expression.Name= Node.Expression.Name(Type(entity.getName()).getShortName()!!)
        var typeparams: Node.TypeParams?=null
        var primconst: Node.Declaration.Class.PrimaryConstructor?=null
        var parents: Node.Declaration.Class.Parents?=null
        var typeconsts: Node.PostModifier.TypeConstraints?=null
        modifiers=createModifiers(entity)
        bodykun=createBody(entity)
        parents=createExtendsAndImplements(entity)
        retClass=Node.Declaration.Class(modifiers,
            Node.Declaration.Class.DeclarationKeyword(Node.Declaration.Class.DeclarationKeyword.Token.CLASS)
            ,name,typeparams,primconst,parents,typeconsts,bodykun)
        return retClass
    }
    private fun createBody(e:ClassDeclaration):Node.Declaration.Class.Body?{
        return Node.Declaration.Class.Body(
            ArrayList<Node.EnumEntry>(),
            false,
            ArrayList<Node.Declaration>()
        )
    }
    private fun createModifiers(e:ClassDeclaration):Node.Modifiers?{
        var retkun:MutableList<Node.Modifier> =ArrayList()
        for(m: Modifier in e.getClassModifiers().getModifiers()) {
            if(m == Modifier.PUBLIC){
                retkun.add(Node.Modifier.Keyword(Node.Modifier.Keyword.Token.PUBLIC))
            }
            if(m == Modifier.PROTECTED){
                retkun.add(Node.Modifier.Keyword(Node.Modifier.Keyword.Token.PROTECTED))
            }
            if(m == Modifier.ABSTRACT){
                retkun.add(Node.Modifier.Keyword(Node.Modifier.Keyword.Token.ABSTRACT))
            }
            if(m == Modifier.PRIVATE){
                retkun.add(Node.Modifier.Keyword(Node.Modifier.Keyword.Token.PRIVATE))
            }
            if(m == Modifier.FINAL){
                retkun.add(Node.Modifier.Keyword(Node.Modifier.Keyword.Token.FINAL))
            }
        }
        return Node.Modifiers(
            retkun
        )
    }
    private fun createExtendsAndImplements(cd:ClassDeclaration):Node.Declaration.Class.Parents?{
        var retlist:MutableList<Node.Declaration.Class.Parent> =ArrayList()
        
        return null
    }

}