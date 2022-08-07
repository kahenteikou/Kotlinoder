package io.github.kahenteikou.kotlinoder.instrumentation

class Scope2Code {
}
final class Utils {
    companion object{
        private var modifierNames:MutableMap<Modifier,String> = HashMap()
        init{
            modifierNames[Modifier.ABSTRACT] = "abstract"
            modifierNames[Modifier.FINAL]="final"
            modifierNames[Modifier.PRIVATE]="private"
            modifierNames[Modifier.PROTECTED]="protected"
            modifierNames[Modifier.PUBLIC]="public"
            modifierNames[Modifier.STATIC]="@JvmStatic"
        }
        fun modifierToName(m:Modifier):String{
            return modifierNames.get(m)!!
        }
    }
    private constructor(){
        throw AssertionError()
    }

}