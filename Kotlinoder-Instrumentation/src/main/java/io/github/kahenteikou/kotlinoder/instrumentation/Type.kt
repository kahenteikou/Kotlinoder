package io.github.kahenteikou.kotlinoder.instrumentation

final class Type : IType {
    private final var packageName:String
    private final var shortName:String
    private final var isReturnOrParamType:Boolean
    constructor(packageName:String,shortName:String,isReturnOrParamType:Boolean) {
        this.packageName = packageName
        this.shortName = shortName
        this.isReturnOrParamType = isReturnOrParamType
        validate()
    }
    private fun validate(){

    }
}