plugins {
    id("java")
    id("application")
    id("org.jetbrains.kotlin.jvm") version ("1.7.10")
    id("com.github.johnrengelman.shadow") version("7.1.2")
    id("org.openjfx.javafxplugin") version ("0.0.13")
}
group="io.github.kahenteikou.kotlinoder"
version="1.0-SNAPSHOT"
application{
    mainClass.set("io.github.kahenteikou.kotlinoder.codevisualization.main.Launcher")
}