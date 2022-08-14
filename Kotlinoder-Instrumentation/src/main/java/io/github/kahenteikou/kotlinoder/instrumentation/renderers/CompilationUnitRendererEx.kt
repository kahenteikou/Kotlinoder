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
    override fun render(e: CompilationUnitDeclaration): Node {
        lateinit var rootNode:Node.KotlinFile
        var annotationSets:MutableList<Node.Modifier.AnnotationSet> = ArrayList()
        var importdirectives: Node.ImportDirectives? = null
        var packageDirective: Node.PackageDirective? = null
        if(e.getPackageName()!=null||e.getPackageName()!!.isEmpty()){
            var names:MutableList<Node.Expression.Name> = ArrayList()
            e.getPackageName()!!.split(".").forEach {
                names.add(Node.Expression.Name(it))
            }
            packageDirective=Node.PackageDirective(null,Node.Keyword.Package(),names)
        }
        return rootNode
    }
}