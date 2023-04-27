package wskim.baselibrary.proguard_safe_zone.exception.base

/**
 * Created by asv on 25.07.13.
 */
open class BaseException : Exception {
    constructor(detailMessage: String?) : super(detailMessage)
    constructor(throwable: Throwable?) : super(throwable)
}