package wskim.main_app.navigation

import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder

object ScreenBundleUtils {
    fun <T> build(data: T): String {
        return URLEncoder.encode(Gson().toJson(data), "UTF-8")
    }

    inline fun <reified T> parse (data: String) : T {
        return Gson().fromJson(URLDecoder.decode(data, "UTF-8"), T::class.java)
    }
}