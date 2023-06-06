package wskim.domain.proguard_safe_zone.network.vo

import wskim.domain.proguard_safe_zone.network.enum.KakaoIntegrationContentTypeEnum
import java.util.Date

data class KakaoIntegrationContentVO (
    var title: String,
    var thumbnail: String,
    var datetime : Date,
    var contentTypeEnum : KakaoIntegrationContentTypeEnum,
    var isBucketYn: Boolean,
    var saveDateTime : Date?
)