package wskim.data.preferences

import android.content.Context

class SharedPreferencesManager(val context: Context) : BaseSharedPreferencesManager(context) {

    fun saveBucketListToString(listToString: String) = putValueToDefaultPrefs(listToString, SP_BUCKET_LIST)
    fun getBucketListToString(): String = defaultPrefs.getString(SP_BUCKET_LIST, "")?:""
}