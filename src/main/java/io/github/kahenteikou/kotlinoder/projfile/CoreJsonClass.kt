package io.github.kahenteikou.kotlinoder.projfile

import com.fasterxml.jackson.annotation.JsonProperty

class CoreJsonClass {
    @JsonProperty("name")
    var name: String=""
    @JsonProperty("transfers")
    var TransferList: List<CoreJson_TransferListElement> = emptyList()
    @JsonProperty("treeelements")
    var TreeElements:List<CoreJsonSubTreeElement> = emptyList()


}