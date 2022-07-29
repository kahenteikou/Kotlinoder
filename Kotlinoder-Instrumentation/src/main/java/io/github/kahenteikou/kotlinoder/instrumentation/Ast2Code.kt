package io.github.kahenteikou.kotlinoder.instrumentation

import com.intellij.openapi.util.Disposer
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiClassInitializer
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory
import org.jetbrains.kotlin.analyzer.AnalysisResult
import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.cli.jvm.compiler.TopDownAnalyzerFacadeForJVM
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.languageVersionSettings
import org.jetbrains.kotlin.context.ContextForNewModule
import org.jetbrains.kotlin.context.ProjectContext
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.storage.StorageManager

class CustomBuiltins(sm : StorageManager) : KotlinBuiltIns(sm)

class Ast2Code {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            var A2=Ast2Code()
            A2.exampleCodeGen()
        }
    }

    fun exampleCodeGen() {

        val config= CompilerConfiguration()
        var dispos= Disposer.newDisposable()
        val env=KotlinCoreEnvironment.createForProduction(
            dispos,
            config,
            EnvironmentConfigFiles.JVM_CONFIG_FILES
        )/*
        val projcontext=ProjectContext(env.project,"Proj An")
        val builtins= CustomBuiltins(projcontext.storageManager)
        val context=ContextForNewModule(projcontext,
        Name.special("<main>"),
        builtins,null)*/
        var ksfactory = KtPsiFactory(env.project)
        var psif= ksfactory.createFile("""
            package io.github.kahenteikou.kotlinoder.instrumentation
            import io.github.kahenteikou.kotlinoder.instrumentation.CustomBuiltins
            @Suppress("unused")
            class A{
                fun foo(){
                    val a=CustomBuiltins(StorageManager.Companion.Empty)
                    println(a.toString())
                }
            }
        """.trimIndent())
        for(elem in psif.children){
            if(elem is KtClass){
                for( anot : KtAnnotationEntry in elem.annotationEntries){
                    println(anot.text)
                }
            }
        }
        println(psif.text)

    }
}