package io.github.kahenteikou.kotlinoder.lang

import ktast.ast.Node
import java.util.regex.Pattern

class VLangUtils {
    companion object{
        @JvmStatic
        fun isPackageNameValid(packageName: String?): Boolean {
            var packageName2=packageName
            if("".equals(packageName2)){
                return true
            }
            if(packageName2 == null){
                packageName2=""
            }
            val identifiers=packageName2.split("\\.")
            for(id in identifiers){
                if(Keywords.isKeyword(id)){
                    return false
                }
            }
            return Patterns.PACKAGE_NAME.matcher(packageName2).matches()
        }
        @JvmStatic
        fun slashToDot(name:String):String{
            return name.replace("/",".")
        }
        @JvmStatic
        fun dotToSlash(name:String):String{
            return name.replace(".","/")
        }
        @JvmStatic
        fun shortNameFromFullClassName(name:String):String{
            var name2=name.replace(".","/")
            val pathkun=name2.split("/")
            if(pathkun.size>0){
                return pathkun[pathkun.size-1]
            }
            return name2
        }
        @JvmStatic
        fun packageNameFromFullClassName(name:String):String{
            var name2=name.replace(".","/")
            val pathkun=name2.split("/")
            var result:String=""
            if(pathkun.size>0){
                for(i in 0 until pathkun.size-1){
                    if(i>0){
                        result+="/"
                    }
                    result+=pathkun[i]
                }
            }
            return result
        }
        @JvmStatic
        fun isShortName(name:String):Boolean{
            return slashToDot(name).equals(shortNameFromFullClassName(name))
        }
        @JvmStatic
        private fun getIdentifierRegex():String{
            return "[a-zA-Z\\p{L}\$_][a-zA-Z\\p{L}\$_0-9]*"
        }
        @JvmStatic
        fun isIdentifierValid(varName:String?,acceptKeywords:Boolean):Boolean{
            var varName2=varName
            if(varName2 == null){
                varName2=""
            }
            val p= Pattern.compile(getIdentifierRegex())
            var resultkun=p.matcher(varName2).matches()
            if(resultkun && !acceptKeywords){
                resultkun=!Keywords.isKeyword(varName2)
            }
            return resultkun
        }
        @JvmStatic
        fun isIdentifierValid(varName:String?):Boolean{
            return isIdentifierValid(varName,true)
        }
        @JvmStatic
        fun isClassNameValid(className:String?):Boolean{
            var className2=className
            if(className2 == null){
                className2=""
            }
            return isIdentifierValid(className2)
        }
        @JvmStatic
        fun addEscapeCharsToCode(code:String?):String{
            var code2:String
            code2 = if(code==null){
                ""
            }else{
                code
            }
            return code2.replace("\"","\\\"")
        }
        @JvmStatic
        fun isVariableNameValid(varName:String?):Boolean{
            var varName2=varName
            if(varName2 == null){
                varName2=""
            }
            return isIdentifierValid(varName2)
        }
        @JvmStatic
        fun packageNameStrToNames(namekun:String):List<Node.Expression.Name>{
            val names:MutableList<Node.Expression.Name> = ArrayList()
            
            return names
        }
    }
}