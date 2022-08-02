package io.github.kahenteikou.kotlinoder.lang

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.logging.Level
import java.util.logging.Logger

class Keywords {
    private var keywords:Set<String> = HashSet<String>()
    companion object{
        init{

        }
        @JvmStatic
        fun readKeyWords(codeName:String):String{
            val iStream:InputStream=Keywords::class.java.getResourceAsStream(codeName)
            val reader=BufferedReader(InputStreamReader(iStream))
            var code:String=""
            try{
                while(reader.ready()){
                    var line:String=reader.readLine()
                    if(line.trim().startsWith('#')||line.trim().isEmpty()){
                        continue
                    }
                    code += "$line\n"
                }
            }catch (ex:IOException){
                Logger.getLogger(Keywords::class.java.name).log(Level.SEVERE,null,ex)
            }finally{
                try{
                    reader.close()
                }catch (ex:IOException){
                    Logger.getLogger(Keywords::class.java.name).log(Level.SEVERE,null,ex)
                }
            }
            return code
        }
    }
}