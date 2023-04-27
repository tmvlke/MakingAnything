package wskim.baselibrary.proguard_safe_zone.network.vo

import wskim.baselibrary.proguard_safe_zone.network.enum.KakaoIntegrationContentTypeEnum
import java.util.*

data class KakaoIntegrationContentVO (
    var title: String,
    var thumbnail: String,
    var datetime : Date,
    var contentTypeEnum : KakaoIntegrationContentTypeEnum,
    var isBucketYn: Boolean,
    var saveDateTime : Date?
)