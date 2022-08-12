package io.github.kahenteikou.kotlinoder.instrumentation

class CompilationUnitRenderer:CodeRenderer<CompilationUnitDeclaration>{
    private var classDeclarationRenderer:CodeRenderer<ClassDeclaration>?=null
    constructor(){

    }
    constructor(cr:CodeRenderer<ClassDeclaration>){
        this.classDeclarationRenderer=cr
    }
}