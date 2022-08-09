package io.github.kahenteikou.kotlinoder.instrumentation

import com.intellij.psi.PsiElement
import eu.mihosoft.vrl.workflow.FlowFactory
import eu.mihosoft.vrl.workflow.IdGenerator
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.allChildren
import java.util.*
import kotlin.collections.ArrayList

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
    fun getrootScope():Scope?{
        return rootScope
    }
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

    fun visitClass(klass: KtClass) {
        //println(">> visitClass: ${klass.name}")
        println("CLASS: ${klass.name}")
        currentScope=codeBuilder.declareClass(
            currentScope as CompilationUnitDeclaration,
            Type(klass.name,false),
            convertModifiers(klass.modifierList),
            convertExtends(klass),Extends()
        )

        for(elemchild in klass.children){
            parse(elemchild)
        }
        currentScope=(currentScope as ClassDeclaration).getParent()
        currentScope?.setCode(klass.text)

    }
    fun visitNamedFunction(kfunc:KtNamedFunction){
        println(">> func : ${kfunc.name}")
        for(elemchild in kfunc.children){
            parse(elemchild)
        }
    }
    fun visitPackageDirective(ktPackageDirective: KtPackageDirective){
        (rootScope as? CompilationUnitDeclaration)?.setPackageName(ktPackageDirective.text)
        for(elemchild in ktPackageDirective.children){
            parse(elemchild)
        }
    }
    fun parse(element: PsiElement){
        for(celem in element.children){
            if(celem is KtClass) {
                visitClass(celem)
            }else if(celem is KtNamedFunction) {
                //visitFunction(celem, 0)
                visitNamedFunction(celem)
            }else if(celem is KtPackageDirective){
                visitPackageDirective(celem)
            }
        }
    }
    private fun convertModifiers(modifiers:KtModifierList?):IModifiers {
        var modifierList:MutableList<Modifier> = ArrayList()
        if(modifiers!!.hasModifier(KtTokens.PUBLIC_KEYWORD)){
            modifierList.add(Modifier.PUBLIC)
        }else if(modifiers!!.hasModifier(KtTokens.PRIVATE_KEYWORD)){
            modifierList.add(Modifier.PRIVATE)
        }
        return Modifiers(modifierList)
    }
    private fun convertExtends(klass: KtClass):IExtends{
        var types:MutableList<Type> = ArrayList()

        if(klass.getSuperTypeList()==null)
            return Extends()
        for(sp in klass.getSuperTypeList()!!.children){
            if(sp is KtSuperTypeEntry){
                types.add(Type(sp.typeReference!!.text,false))
            }
        }
        return Extends(types)
    }
}