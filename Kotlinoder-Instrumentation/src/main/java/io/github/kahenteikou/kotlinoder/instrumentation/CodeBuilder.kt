package io.github.kahenteikou.kotlinoder.instrumentation

class CodeBuilder {
    private var indentString:String="    "
    private var currentLine:StringBuilder = StringBuilder()
    private var cursorPos:Int=0
    private var code:ArrayList<Line> =ArrayList()
    fun getIndentString():String{
        return indentString
    }
    private fun setIndentDepth(depth:Int){
        var result:String=""
        for(i in 1..depth){
            result += " "
        }
        indentString = result
    }
    fun decIndentation():CodeBuilder{
        if(cursorPos > 0){
            cursorPos--
        }
        return this
    }
    fun incIndentation():CodeBuilder{
        cursorPos++
        return this
    }
    private fun getIndentation(pos:Int):String{
        var result:String=""
        for(i in 1..pos){
            result += getIndentString()
        }
        return result
    }
    companion object{
        private class Line{
            private var indentCount:Int
            private var line:String
            constructor(line:String,indentCount:Int){
                this.indentCount = indentCount
                this.line = line
            }
            constructor(line:String){
                this.indentCount = 0
                this.line = line
            }
            fun getLine():String{
                return line
            }
            fun getIndentCount():Int{
                return indentCount
            }
        }
    }
}