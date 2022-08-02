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
    }
}