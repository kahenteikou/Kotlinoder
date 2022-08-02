package io.github.kahenteikou.kotlinoder.lang

class Patterns {
    companion object{
        @JvmStatic
        final var IDENTIFIER_STRING="[a-zA-Z\\\\p{L}\$_][a-zA-Z\\\\p{L}\$_0-9]*"
        final var PACKAGE_NAME_STRING="($IDENTIFIER_STRING)(\\.$IDENTIFIER_STRING)*"
    }
}