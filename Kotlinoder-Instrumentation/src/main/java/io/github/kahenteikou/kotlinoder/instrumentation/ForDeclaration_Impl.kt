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
    fun getVarName():String{
        return varName
    }
    fun getFrom():Int{
        return _from
    }
    fun getTo():Int{
        return _to
    }
    fun getInc():Int{
        return _inc
    }
    fun setVarName(varName:String){
        this.varName=varName
    }
    fun setFrom(from:Int){
        this._from=from
    }
    fun setTo(to:Int){
        this._to=to
    }
    fun setInc(inc:Int){
        this._inc=inc
    }
    
}