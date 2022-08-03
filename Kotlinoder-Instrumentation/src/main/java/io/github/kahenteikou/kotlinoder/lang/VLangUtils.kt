package io.github.kahenteikou.kotlinoder.lang

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
        fun isIdentifierValid(varName:String,acceptKeywords:Boolean):Boolean{

        }
    }
}