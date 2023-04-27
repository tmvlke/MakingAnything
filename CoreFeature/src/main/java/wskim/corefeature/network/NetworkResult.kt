package wskim.corefeature.network

import wskim.baselibrary.proguard_safe_zone.network.vo.KakaoMetaVO

class NetworkResult<T> private constructor(
    private val documents: ArrayList<T>?,
    private val meta: KakaoMetaVO?,
    private val errorType: String,
    private val exception: Throwable?,
) {
    val isSuccessful: Boolean
        get() = exception == null

    constructor(value: ArrayList<T>?, meta: KakaoMetaVO?) : this(value, meta, "",  null)

    constructor(errorType: String, exception: Throwable) : this(null, null, errorType, exception)

    fun getDocuments(): ArrayList<T>? {
        if (exception != null)
            throw IllegalStateException("Cannot return value for error result")
        return documents
    }

    fun getMeta(): KakaoMetaVO? {
        if (exception != null)
            throw IllegalStateException("Cannot return value for error result")
        return meta
    }

    fun getErrorType() : String {
        return errorType
    }

    fun getException(): Throwable {
        if (exception == null)
            throw IllegalStateException("Cannot return exception for successful result")
        return exception
    }
}