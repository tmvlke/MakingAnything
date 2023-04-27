package wskim.corefeature

import android.util.Log
import wskim.baselibrary.BaseLibrarySettings
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.charset.StandardCharsets

object BaseLog {

    fun d(tag : String, msg : Any?){
        if(BaseLibrarySettings.isDebugMode()){
            Log.d(tag, msg.toString())
        }
    }

    fun w(tag : String, msg : Any?){
        if(BaseLibrarySettings.isDebugMode()){
            Log.w(tag, msg.toString())
        }
    }

    fun i(tag : String, msg : Any?){
        if(BaseLibrarySettings.isDebugMode()){
            Log.i(tag, msg.toString())
        }
    }

    fun e(tag : String = "", throwable : Throwable){
        if(BaseLibrarySettings.isDebugMode()){
            Log.e(tag, thrToStringWithStack(throwable))
        }
    }

    private fun thrToString(t: Throwable): String {
        return t.javaClass.name + ": " + t.message
    }

    private fun thrToStringWithStack(t: Throwable): String {
        var result = thrToString(t)
        try {
            val out = ByteArrayOutputStream()
            val printStream = PrintStream(out)
            t.printStackTrace(printStream)
            printStream.close()
            out.close()
            result += """

                ${String(out.toByteArray(), StandardCharsets.UTF_8)}
                """
        } catch (t1: Throwable) { /*ignored*/
        }
        return result
    }
}