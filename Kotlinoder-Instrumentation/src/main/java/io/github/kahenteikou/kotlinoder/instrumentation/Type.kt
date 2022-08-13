package io.github.kahenteikou.kotlinoder.instrumentation

import io.github.kahenteikou.kotlinoder.lang.VLangUtils

final class Type : IType {
    private final var packageName:String
    override fun getPackageName(): String {
        return packageName
    }

    private final var shortName:String?
    override fun getShortName(): String? {
        return shortName
    }

    private final var isReturnOrParamType:Boolean
    constructor(packageName:String,shortName:String,isReturnOrParamType:Boolean) {
        this.packageName = packageName
        this.shortName = shortName
        this.isReturnOrParamType = isReturnOrParamType
        validate()
    }
    constructor(packageName:String,shortName:String) :this(packageName,shortName,false){
        validate()
    }
    constructor(fullName:String?):this(fullName,false){
        validate()
    }
    constructor(fullName:String?,isReturnOrParamType: Boolean){
        //complete!!
        if(!VLangUtils.isShortName(fullName!!)){
            this.packageName=VLangUtils.slashToDot(
                VLangUtils.packageNameFromFullClassName(fullName!!)
            )
            this.shortName=VLangUtils.shortNameFromFullClassName(fullName!!)
        }else{
            this.packageName=""
            this.shortName=fullName
        }
        this.isReturnOrParamType=isReturnOrParamType

        validate()
    }

    private fun validate(){
        if(!VLangUtils.isPackageNameValid(VLangUtils.slashToDot(packageName))){
            throw IllegalArgumentException("Specified package is invalid: " + getPackageName())
        }
        if(!isReturnOrParamType){
            if(!VLangUtils.isClassNameValid(shortName)){
                throw IllegalArgumentException("Specified classname is invalid: " + getShortName())
            }
        }else{
            if(!VLangUtils.isIdentifierValid(shortName,true)){
                throw IllegalArgumentException("Specified classname is invalid: " + getShortName())
            }
        }
    }

    override fun getFullClassName(): String? {
        if(packageName.isEmpty()){
            return shortName
        }else{
            return packageName+"."+shortName
        }
    }

    override fun hashCode(): Int {
        var hash:Int=7
        hash=71*hash+java.util.Objects.hashCode(this.packageName)
        hash=71*hash+java.util.Objects.hashCode(this.shortName)
        return hash
    }

    override fun toString(): String {
        return "[pck:$packageName, name:$shortName]"
    }

    override fun equals(other: Any?): Boolean {
        if(other==null){
            return false
        }
        if(this.javaClass != other.javaClass){
            return false
        }
        var other:Type=other as Type
        if(!java.util.Objects.equals(this.packageName,other.packageName)){
            return false
        }
        if(!java.util.Objects.equals(this.shortName,other.shortName)){
            return false
        }
        return true
    }
}