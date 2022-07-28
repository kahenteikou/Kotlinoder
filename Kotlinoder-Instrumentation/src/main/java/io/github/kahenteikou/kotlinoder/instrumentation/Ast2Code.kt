package io.github.kahenteikou.kotlinoder.instrumentation

import com.intellij.openapi.util.Disposer
import org.jetbrains.kotlin.analyzer.AnalysisResult
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.context.ProjectContext

class Ast2Code {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {

        }
    }
    fun exampleCodeGen(): AnalysisResult? {
        val config= CompilerConfiguration()
        var dispos= Disposer.newDisposable()
        val env=KotlinCoreEnvironment.createForProduction(
            dispos,
            config,
            EnvironmentConfigFiles.JVM_CONFIG_FILES
        )
        
        return null
    }
}