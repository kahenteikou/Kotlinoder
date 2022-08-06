package io.github.kahenteikou.kotlinoder.instrumentation

class ForDeclaration_Impl {
}
class ForDeclarationMetaData {
    private var varName:String
    private var _from:Int
    private var _to:Int
    private var _inc:Int
    constructor(varName:String,_from:Int,_to:Int,_inc:Int){
        this.varName=varName
        this._from=_from
        this._to=_to
        this._inc=_inc
    }
}