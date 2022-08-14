package io.github.kahenteikou.kotlinoder.instrumentation.renderers

import io.github.kahenteikou.kotlinoder.instrumentation.ClassDeclaration
import io.github.kahenteikou.kotlinoder.instrumentation.CompilationUnitDeclaration
import ktast.ast.Node

class CompilationUnitRendererEx:CodeRendererEx<CompilationUnitDeclaration,Node> {
    private var classDeclarationRenderer:CodeRendererEx<ClassDeclaration,Node.Declaration>? = null
    constructor(){

    }
    constructor(classrenderer:CodeRendererEx<ClassDeclaration,Node.Declaration>){
        classDeclarationRenderer=classrenderer
    }
    override fun render(e: CompilationUnitDeclaration): Node {
        lateinit var rootNode:Node.KotlinFile
        var annotationSets:MutableList<Node.Modifier.AnnotationSet> = ArrayList()
        var importdirectives: Node.ImportDirectives? = null
        var packageDirective: Node.PackageDirective? = null
        var declarations:MutableList<Node.Declaration> = ArrayList()
        if(e.getPackageName()!=null||e.getPackageName()!!.isEmpty()){
            var names:MutableList<Node.Expression.Name> = ArrayList()
            e.getPackageName()!!.split(".").forEach {
                names.add(Node.Expression.Name(it))
            }
            packageDirective=Node.PackageDirective(null,Node.Keyword.Package(),names)
        }
        if(classDeclarationRenderer!=null)
        for(cd in e.getDeclaredClasses()){
            declarations.add(classDeclarationRenderer!!.render(cd))
        }
        rootNode=Node.KotlinFile(annotationSets,packageDirective,importdirectives,declarations)
        return rootNode
    }
}