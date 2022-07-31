package io.github.kahenteikou.kotlinoder.instrumentation

final class Type : IType {
    private final var packageName:String
    override fun getPackageName(): String {
        return packageName
    }

    private final var shortName:String
    override fun getShortName(): String {
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
    constructor(fullName:String):this(fullName,false){
        validate()
    }
    constructor(fullName:String,isReturnOrParamType: Boolean){
        this.shortName=fullName
        this.packageName=""
        this.isReturnOrParamType=isReturnOrParamType
        validate()
    }

    private fun validate(){

    }

    override fun getFullClassName(): String {
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
    }
}