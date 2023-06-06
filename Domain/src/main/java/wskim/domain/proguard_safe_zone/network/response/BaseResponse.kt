package wskim.domain.proguard_safe_zone.network.response

import wskim.domain.proguard_safe_zone.network.vo.KakaoMetaVO

open class BaseResponse<T> {
    var documents: ArrayList<T>? = null
    var meta: KakaoMetaVO? = null
    var errorType: String = ""
    var message: String = ""
}
