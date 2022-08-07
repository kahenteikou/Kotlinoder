package io.github.kahenteikou.kotlinoder.instrumentation

import com.intellij.psi.PsiElement
import eu.mihosoft.vrl.workflow.FlowFactory
import eu.mihosoft.vrl.workflow.IdGenerator
import org.jetbrains.kotlin.psi.*
import java.util.*

class KotlinVisualizationTransformation {
}
class KotlinCodeVisitor{
    private var codeBuilder:VisualCodeBuilder_Impl
    private var ktFile:KtFile
    private var rootScope:Scope?=null
    private var currentScope:Scope?=null
    private var lastMethod:Invocation?=null
    private var vIdStack: Stack<String> = Stack()
    private var generator:IdGenerator=FlowFactory.newIdGenerator()
    constructor(ktFile:KtFile,codeBuilder:VisualCodeBuilder_Impl){

        this.codeBuilder=codeBuilder
        this.ktFile=ktFile
        codeBuilder.setIdRequest(object : IdRequest{
            override fun request(): String {
                return requestId()
            }

        })

        this.rootScope=codeBuilder.declareCompilationUnit(
            ktFile.name,
            "undefined"
        )
        this.currentScope=rootScope
    }
    private fun requestId():String{
        var result:String=""
        if(!vIdStack.isEmpty()){
            result=vIdStack.pop()
            if(generator.ids.contains(result)){
                error(">> requestId(): id already exists: $result")
                result=generator.newId()
            }else{
                generator.addId(result)
                println(">> USING ID: $result")
            }
        }else{
            result=generator.newId()
        }
        return result
    }

    fun visitClass(klass: KtClass, data: Int?) {
        println(">> visitClass: ${klass.name}")

    }
    fun parse(element: PsiElement){
        for(celem in element.children){
            if(celem is KtClass) {
                visitClass(celem, 0)
            }else if(celem is KtFunction) {
                //visitFunction(celem, 0)
            }
            for(elemchild in celem.children){
                parse(elemchild)
            }
        }
    }
}