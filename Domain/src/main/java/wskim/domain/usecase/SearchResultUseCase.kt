package wskim.domain.usecase

import wskim.domain.proguard_safe_zone.network.NetworkResult
import wskim.domain.proguard_safe_zone.network.vo.KakaoImageVO
import wskim.domain.proguard_safe_zone.network.vo.KakaoIntegrationContentListVO
import wskim.domain.proguard_safe_zone.network.vo.KakaoVclipVO
import wskim.domain.repository.SearchResultRepository
import javax.inject.Inject

class SearchResultUseCase @Inject constructor(private val repository: SearchResultRepository){

    suspend fun selectDummyData(
        page: Int = 1,
        count: Int = 20
    ): ArrayList<String> = repository.selectDummyData(page, count)

    suspend fun selectKakaoImageList(keyword: String, page: Int, size: Int) : NetworkResult<KakaoImageVO> = repository.selectKakaoImageList(keyword, page, size)
    suspend fun selectKakaoVclipList(keyword: String, page: Int, size: Int) : NetworkResult<KakaoVclipVO> = repository.selectKakaoVclipList(keyword, page, size)
    fun selectBucketList(): KakaoIntegrationContentListVO = repository.selectBucketList()
    fun updateBucketList(list: KakaoIntegrationContentListVO) = repository.updateBucketList(list)
}