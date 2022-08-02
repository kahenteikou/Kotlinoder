package io.github.kahenteikou.kotlinoder.lang

class VLangUtils {
    companion object{
        @JvmStatic
        fun isPackageNameValid(packageName: String?): Boolean {
            val packageName2=packageName
            if("".equals(packageName2)){
                return true
            }
            if(packageName2 == null){
                packageName2=""
            }
            val identifiers=packageName2.split("\\.")
            for(id in identifiers){
                if(KeyWords)
            }
        }
    }
}