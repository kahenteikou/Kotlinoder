package io.github.kahenteikou.kotlinoder.instrumentation.renderers

import io.github.kahenteikou.kotlinoder.instrumentation.ClassDeclaration
import io.github.kahenteikou.kotlinoder.instrumentation.MethodDeclaration
import io.github.kahenteikou.kotlinoder.instrumentation.Type
import ktast.ast.Node

class ClassDeclarationRendererEx:CodeRendererEx<ClassDeclaration, Node.Declaration.Class> {
    private var _methodDeclarationRenderer:CodeRendererEx<MethodDeclaration,Node.Declaration>?=null
    var methodDeclarationRenderer:CodeRendererEx<MethodDeclaration,Node.Declaration>
        get() = _methodDeclarationRenderer!!
        set(value) {
            _methodDeclarationRenderer = value
        }
    override fun render(entity: ClassDeclaration): Node.Declaration.Class {
        lateinit var retClass:Node.Declaration.Class
        var bodykun:Node.Declaration.Class.Body?=null
        var modifiers:Node.Modifiers?=null
        var name: Node.Expression.Name= Node.Expression.Name(Type(entity.getName()).getShortName()!!)
        var typeparams: Node.TypeParams?=null
        var primconst: Node.Declaration.Class.PrimaryConstructor?=null
        
        return retClass
    }
    private fun createBody(e:ClassDeclaration):Node.Declaration.Class.Body?{
        return null
    }

}