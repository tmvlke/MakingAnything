package wskim.data.preferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

@SuppressLint("CommitPrefEdits")
open class BaseSharedPreferencesManager(context: Context) {
    private val privateAppPrefs: SharedPreferences by lazy {
        context.getSharedPreferences(PRIVATE_APP_PREFS_NAME, Context.MODE_PRIVATE)
    }

    private val privateAppPrefsEditor: SharedPreferences.Editor by lazy {
        privateAppPrefs.edit()
    }

    protected val defaultPrefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    private val defaultPrefsEditor: SharedPreferences.Editor by lazy {
        defaultPrefs.edit()
    }

    /**
     * don't use PUT functions with variables which can be null
     *
     * if u want save variable which can be null
     * */
    protected fun putValueToPrivateAppPrefs(value: Any, key: String) = putValueToPref(privateAppPrefsEditor, value, key)
    protected fun putValueToDefaultPrefs(value: Any, key: String) = putValueToPref(defaultPrefsEditor, value, key)
    private fun putValueToPref(prefEditor: SharedPreferences.Editor, value: Any, key: String) {
        when (value) {
            is String -> prefEditor.putString(key, value).apply()
            is Boolean -> prefEditor.putBoolean(key, value).apply()
            is Int -> prefEditor.putInt(key, value).apply()
            is Long -> prefEditor.putLong(key, value).apply()
            is Float -> prefEditor.putFloat(key, value).apply()
        }
    }

    // separate  method to put strings set
    protected fun putValueToDefaultPrefs(value: Set<String>, key: String) = putValueToPref(defaultPrefsEditor, value, key)
    private fun putValueToPref(prefEditor: SharedPreferences.Editor, value: Set<String>, key: String) {
        prefEditor.putStringSet(key, value).apply()
    }

    protected fun removeValueFromPrivateAppPrefs(key: String) = removeValueFromPref(privateAppPrefsEditor, key)
    protected fun removeValueFromDefaultPrefs(key: String) = removeValueFromPref(defaultPrefsEditor, key)
    private fun removeValueFromPref(prefEditor: SharedPreferences.Editor, key: String) = prefEditor.remove(key).apply()

    companion object {
        const val PRIVATE_APP_PREFS_NAME = "app_prefs"

        const val SP_BUCKET_LIST = "BUCKET_LIST"
    }
}