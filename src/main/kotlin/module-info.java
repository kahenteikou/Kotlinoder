module Kotlinoder.main {
    requires kotlin.stdlib;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;

    exports io.github.kahenteikou.kotlinoder;
    opens io.github.kahenteikou.kotlinoder;
}