package io.github.kahenteikou.kotlinoder.projfile

import com.fasterxml.jackson.annotation.JsonProperty

class CoreJsonClass {
    @JsonProperty("name")
    var name: String=""
    var TransferList: List<CoreJson_TransferListElement> = emptyList()
    

}