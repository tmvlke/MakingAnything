package wskim.baselibrary.proguard_safe_zone.network.vo

import java.util.*

data class KakaoImageVO (
    var collection : String,
    var thumbnail_url : String,
    var width : Int,
    var height : Int,
    var display_sitename : String,
    var doc_url : String,
    var datetime : Date
)