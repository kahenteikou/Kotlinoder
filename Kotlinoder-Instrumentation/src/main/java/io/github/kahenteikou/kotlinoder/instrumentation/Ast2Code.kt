package io.github.kahenteikou.kotlinoder.instrumentation

import com.intellij.openapi.util.Disposer
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
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.storage.StorageManager

class CustomBuiltins(sm : StorageManager) : KotlinBuiltIns(sm)
class Ast2Code {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            var A2=Ast2Code()
            println(A2.exampleCodeGen().fileType.description);
        }
    }

    fun exampleCodeGen(): KtFile {

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
        var psif= KtPsiFactory(env.project).createFile("""
            fun main(args: Array<String>) {
                println("Hello, world!")
            }
        """.trimIndent()) as KtFile
        return psif
    }
}