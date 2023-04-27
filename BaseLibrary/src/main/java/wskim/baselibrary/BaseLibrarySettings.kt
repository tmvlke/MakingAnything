package wskim.baselibrary

import android.util.Log

/**
 * Created by asv on 18.11.2014.
 */
object BaseLibrarySettings {
    private val TAG = BaseLibrarySettings::class.java.name
    private var initialized = false
    private var enableDebugMode = false

    fun isDebugMode(): Boolean {
        if (!initialized) Log.w(
            TAG,
            "BaseLibrarySettings 의 초기화가 필요합니다."
        )
        return enableDebugMode
    }

    fun init(enableDebugMode: Boolean) {
        BaseLibrarySettings.enableDebugMode = enableDebugMode
        initialized = true
    }
}