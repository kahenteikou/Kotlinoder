package io.github.kahenteikou.kotlinoder.instrumentation

class CodeBuilder {
    companion object{
        private class Line{
            private var indentCount:Int
            private var line:String
            constructor(line:String,indentCount:Int){
                this.indentCount = indentCount
                this.line = line
            }
        }
    }
}