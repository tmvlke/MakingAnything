package wskim.baselibrary.proguard_safe_zone.network.vo

data class KakaoIntegrationContentListVO (
    var list: ArrayList<KakaoIntegrationContentVO>,
    var listAddTypeEnum: ListAddTypeEnum,
    var deletePosition: Int = -1,
) {
    enum class ListAddTypeEnum {
        REFRESH,
        ADD,
        DELETE
    }
}