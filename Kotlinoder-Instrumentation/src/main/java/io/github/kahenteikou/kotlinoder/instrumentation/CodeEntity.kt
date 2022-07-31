package io.github.kahenteikou.kotlinoder.instrumentation

/**
 * Code Entity
 */
interface CodeEntity {
    fun getId() : String
    fun setId(id:String)
    fun getCode() : String
    fun setCode(code:String)

}