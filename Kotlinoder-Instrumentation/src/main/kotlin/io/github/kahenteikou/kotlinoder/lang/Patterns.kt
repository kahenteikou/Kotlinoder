package io.github.kahenteikou.kotlinoder.lang

import java.util.regex.Pattern

class Patterns {
    companion object{
        @JvmStatic
        final val IDENTIFIER_STRING="[a-zA-Z\\\\p{L}\$_][a-zA-Z\\\\p{L}\$_0-9]*"
        @JvmStatic
        final val PACKAGE_NAME_STRING="($IDENTIFIER_STRING)(\\.$IDENTIFIER_STRING)*"
        @JvmStatic
        final val PACKAGE_NAME= Pattern.compile(PACKAGE_NAME_STRING,
        Pattern.DOTALL)

    }
}