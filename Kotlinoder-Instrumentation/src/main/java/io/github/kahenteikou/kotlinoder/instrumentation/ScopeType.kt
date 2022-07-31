package io.github.kahenteikou.kotlinoder.instrumentation

enum class ScopeType {
    COMPILATION_UNIT,
    CLASS,
    INTERFACE,
    METHOD,
    CLOSURE,
    FOR,
    WHILE,
    IF,
    ELSE,
    NONE
}