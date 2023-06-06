package wskim.data.repository

import com.google.gson.Gson
import wskim.data.network.manager.NetworkCommonManager
import wskim.data.preferences.SharedPreferencesManager
import wskim.domain.proguard_safe_zone.network.vo.KakaoIntegrationContentListVO

@Deprecated("삭제 예정")
class SearchResultRepository(
    private val networkCommonManager: NetworkCommonManager,
    private val sharedPreferencesManager: SharedPreferencesManager
    ) {

    suspend fun selectKakaoImageList(keyword: String, page: Int, size: Int) = networkCommonManager.selectKakaoImageList(
        query = keyword,
        page = page,
        size = size
    )

    suspend fun selectKakaoVclipList(keyword: String, page: Int, size: Int) = networkCommonManager.selectKakaoVclipList(
        query = keyword,
        page = page,
        size = size
    )

    fun selectBucketList(): KakaoIntegrationContentListVO {
        return sharedPreferencesManager.getBucketListToString().let {
            if (it.isEmpty()) {
                KakaoIntegrationContentListVO(
                    list = arrayListOf(),
                    listAddTypeEnum = KakaoIntegrationContentListVO.ListAddTypeEnum.REFRESH
                )
            } else {
                Gson().fromJson(it, KakaoIntegrationContentListVO::class.java)
            }
        }
    }

    fun updateBucketList(list: KakaoIntegrationContentListVO) {
        return sharedPreferencesManager.saveBucketListToString(Gson().toJson(list))
    }
}