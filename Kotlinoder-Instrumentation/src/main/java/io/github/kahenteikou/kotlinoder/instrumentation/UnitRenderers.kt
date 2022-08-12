package io.github.kahenteikou.kotlinoder.instrumentation

import ktast.ast.Node
import ktast.ast.psi.Parser

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
        var nd= Parser.parseFile("")
        render(entity,nd)
        return nd
    }

    override fun render(e: CompilationUnitDeclaration, nd: Node) {

        if(e.getPackageName()!=null||e.getPackageName()!!.isEmpty()){
            if(nd is Node.KotlinEntry){
                nd.packageDirective=Node.PackageDirective(null,Node.Keyword.Package(),null)
                
            }
            //cb.append("package ").append(e.getPackageName()!!).newLine().newLine()

        }
        for(cd in e.getDeclaredClasses()){
            //classDeclarationRenderer!!.render2(cd,nd)
        }
    }
}