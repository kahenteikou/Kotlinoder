module io.github.kahenteikou.kotlinoder.instrumentation {
    requires kotlin.stdlib;
    exports io.github.kahenteikou.kotlinoder.instrumentation;
    exports io.github.kahenteikou.kotlinoder.lang;
    requires ast.jvm;
    requires ast.psi;
    requires java.logging;
}