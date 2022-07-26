package io.github.kahenteikou.kotlinoder.projfile

import java.util.*

class CoreJsonSubTreeElement {
    var uuid: UUID=UUID.fromString("00000000-0000-0000-0000-000000000000")
    var targets:MutableMap<String,CoreJsonSubTreeConnectorElement> =mutableMapOf()
    
}