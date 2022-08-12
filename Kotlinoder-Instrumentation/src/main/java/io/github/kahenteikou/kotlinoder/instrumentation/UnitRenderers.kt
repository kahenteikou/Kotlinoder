package io.github.kahenteikou.kotlinoder.instrumentation

import io.github.kahenteikou.kotlinoder.lang.VLangUtils
import ktast.ast.Node
import ktast.ast.psi.Converter.Companion.map
import ktast.ast.psi.Parser

class ClassDeclarationRenderer:CodeRenderer<ClassDeclaration>{

    private var methodDeclarationRenderer: CodeRenderer<MethodDeclaration>
    constructor(methodDeclarationRenderer: CodeRenderer<MethodDeclaration>){
        this.methodDeclarationRenderer = methodDeclarationRenderer
    }

    override fun render(entity: ClassDeclaration): Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun render(entity: ClassDeclaration, nd: Node) {
    }

}
class CompilationUnitRenderer:CodeRenderer<CompilationUnitDeclaration>{
    private var classDeclarationRenderer:CodeRenderer<ClassDeclaration>?=null
    constructor(){

    }
    constructor(cr:CodeRenderer<ClassDeclaration>){
        this.classDeclarationRenderer=cr
    }

    fun getClassDeclarationRenderer():CodeRenderer<ClassDeclaration>{
        return classDeclarationRenderer!!
    }
    fun setClassDeclarationRenderer(classDeclarationRenderer:CodeRenderer<ClassDeclaration>){
        this.classDeclarationRenderer=classDeclarationRenderer
    }

    override fun render(entity: CompilationUnitDeclaration): Node {

        var nd= Parser.parseFile("package ${entity.getPackageName()!!}")
        render(entity,nd)
        return nd
    }

    override fun render(e: CompilationUnitDeclaration, nd: Node) {

        for(cd in e.getDeclaredClasses()){
            classDeclarationRenderer?.render(cd,nd)
        }
    }
}