package io.github.kahenteikou.kotlinoder.instrumentation.renderers

import io.github.kahenteikou.kotlinoder.instrumentation.ClassDeclaration
import io.github.kahenteikou.kotlinoder.instrumentation.MethodDeclaration
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
        return retClass
    }

}