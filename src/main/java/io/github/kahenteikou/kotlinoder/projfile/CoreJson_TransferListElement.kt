package io.github.kahenteikou.kotlinoder.projfile

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * CoreJsonTransfer
 */
class CoreJson_TransferListElement {
    /**
     * @param name name
     * @param value val
     */
    @JsonProperty("number")
    var number:Long=0
    @JsonProperty("name")
    var name:String=""
}