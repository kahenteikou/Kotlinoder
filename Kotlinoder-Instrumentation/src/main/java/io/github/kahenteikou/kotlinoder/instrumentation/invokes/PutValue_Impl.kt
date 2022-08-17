package io.github.kahenteikou.kotlinoder.instrumentation.invokes

import io.github.kahenteikou.kotlinoder.instrumentation.Scope
import io.github.kahenteikou.kotlinoder.instrumentation.Variable

open class PutValue_Impl:PutValue {

    private var _id : String
    private final var parent: Scope
    private var _SrcVariable: Variable
    private var _DstVariable: Variable

    constructor(parent:Scope,id:String,srcVar:Variable,dstVar:Variable){
            this._id = id
        this.parent = parent
        this._SrcVariable = srcVar
        this._DstVariable = dstVar
    }

    override fun getSrcVariable(): Variable {
        return _SrcVariable
    }

    override fun getDstVariable(): Variable {
        return _DstVariable
    }

}