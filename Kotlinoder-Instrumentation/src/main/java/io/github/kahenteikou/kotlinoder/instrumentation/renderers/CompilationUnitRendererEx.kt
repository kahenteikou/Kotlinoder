package io.github.kahenteikou.kotlinoder.instrumentation.renderers

import io.github.kahenteikou.kotlinoder.instrumentation.ClassDeclaration
import io.github.kahenteikou.kotlinoder.instrumentation.CompilationUnitDeclaration
import ktast.ast.Node

class CompilationUnitRendererEx:CodeRendererEx<CompilationUnitDeclaration> {
    private var classDeclarationRenderer:CodeRendererEx<ClassDeclaration>? = null
    constructor(){

    }
    constructor(classrenderer:CodeRendererEx<ClassDeclaration>){
        classDeclarationRenderer=classrenderer
    }
    override fun render(entity: CompilationUnitDeclaration): Node {
        
    }
}