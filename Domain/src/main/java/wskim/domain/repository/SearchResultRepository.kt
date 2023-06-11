package wskim.domain.repository

import wskim.domain.proguard_safe_zone.network.NetworkResult
import wskim.domain.proguard_safe_zone.network.vo.KakaoImageVO
import wskim.domain.proguard_safe_zone.network.vo.KakaoIntegrationContentListVO
import wskim.domain.proguard_safe_zone.network.vo.KakaoVclipVO

interface SearchResultRepository {

    suspend fun selectDummyData(
        page: Int,
        count: Int
    ): ArrayList<String>

    suspend fun selectKakaoImageList(keyword: String, page: Int, size: Int) : NetworkResult<KakaoImageVO>
    suspend fun selectKakaoVclipList(keyword: String, page: Int, size: Int) : NetworkResult<KakaoVclipVO>
    fun selectBucketList(): KakaoIntegrationContentListVO
    fun updateBucketList(list: KakaoIntegrationContentListVO)
}