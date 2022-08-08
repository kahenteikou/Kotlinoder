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
dependencies{
    implementation("org.jfxtras:jfxtras-labs:9.0-r1")
    implementation("eu.mihosoft.vrl.workflow:vworkflows-core:0.2.5.0")
    implementation("eu.mihosoft.vrl.workflow:vworkflows-fx:0.2.5.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.7.10")
    implementation("org.jetbrains.kotlin:kotlin-test:1.7.10")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.7.10")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}
javafx{
    version="17"
    modules= arrayOf("javafx.controls", "javafx.fxml").toMutableList()
}
val jar by tasks.getting(Jar::class){
    manifest{
        attributes["Main-Class"]= "io.github.kahenteikou.kotlinoder.codevisualization.main.Launcher"
    }
    duplicatesStrategy= DuplicatesStrategy.EXCLUDE
}