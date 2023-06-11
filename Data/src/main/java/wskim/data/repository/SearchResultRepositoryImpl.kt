package wskim.data.repository

import com.google.gson.Gson
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import wskim.data.network.manager.NetworkCommonManager
import wskim.data.preferences.SharedPreferencesManager
import wskim.domain.proguard_safe_zone.network.vo.KakaoIntegrationContentListVO
import wskim.domain.repository.SearchResultRepository

class SearchResultRepositoryImpl(
    private val networkCommonManager: NetworkCommonManager,
    private val sharedPreferencesManager: SharedPreferencesManager
) : SearchResultRepository {

    private fun makeDummyList(): ArrayList<String> {
        val dummyList = arrayListOf<String>()

        repeat(1000) {
            dummyList.add("${it}번 데이터")
        }
        return dummyList
    }

    override suspend fun selectDummyData(
        page: Int,
        count: Int
    ) : ArrayList<String> {
        val allData = makeDummyList()

        return allData.asFlow().drop((page-1) * count).take(count).toList() as ArrayList<String>
    }

    override suspend fun selectKakaoImageList(keyword: String, page: Int, size: Int) =
        networkCommonManager.selectKakaoImageList(
            query = keyword,
            page = page,
            size = size
        )

    override suspend fun selectKakaoVclipList(keyword: String, page: Int, size: Int) =
        networkCommonManager.selectKakaoVclipList(
            query = keyword,
            page = page,
            size = size
        )

    override fun selectBucketList(): KakaoIntegrationContentListVO {
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

    override fun updateBucketList(list: KakaoIntegrationContentListVO) {
        return sharedPreferencesManager.saveBucketListToString(Gson().toJson(list))
    }
}