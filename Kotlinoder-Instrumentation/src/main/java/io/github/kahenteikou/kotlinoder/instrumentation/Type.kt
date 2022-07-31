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

    private fun validate(){

    }
}