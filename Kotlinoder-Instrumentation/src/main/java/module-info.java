module io.github.kahenteikou.kotlinoder.instrumentation {
    requires kotlin.stdlib;
    requires ast.jvm;
    requires ast.psi;
    requires java.logging;
    exports io.github.kahenteikou.kotlinoder.instrumentation;
    exports io.github.kahenteikou.kotlinoder.lang;
}