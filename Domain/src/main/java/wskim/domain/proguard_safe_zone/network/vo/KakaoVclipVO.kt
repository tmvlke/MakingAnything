package wskim.domain.proguard_safe_zone.network.vo

import java.util.Date

data class KakaoVclipVO (
    var title : String,
    var play_time : Int,
    var thumbnail : String,
    var url : String,
    var datetime : Date,
    var author : String
)