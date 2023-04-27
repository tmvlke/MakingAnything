package wskim.corefeature.network.manager

import android.content.Context
import wskim.corefeature.network.NetworkRequestCoroutine
import wskim.corefeature.network.NetworkResult
import wskim.baselibrary.proguard_safe_zone.network.vo.KakaoImageVO
import wskim.baselibrary.proguard_safe_zone.network.vo.KakaoVclipVO
import wskim.corefeature.network.retrofit.ServerAPI

class NetworkCommonManager(
    context: Context,
    private val restService: ServerAPI
) : BaseNetworkManager(context) {

    suspend fun selectKakaoImageList(
        query: String,
        page: Int,
        size: Int
    ) : NetworkResult<KakaoImageVO> {
        super.preTreatment()

        return NetworkRequestCoroutine<KakaoImageVO>().execute(restService.selectKakaoImageListAsync(
            query = query,
            page = page,
            size = size
        ))
    }

    suspend fun selectKakaoVclipList(
        query: String,
        page: Int,
        size: Int
    ) : NetworkResult<KakaoVclipVO> {
        super.preTreatment()

        return NetworkRequestCoroutine<KakaoVclipVO>().execute(restService.selectKakaoVclipListAsync(
            query = query,
            page = page,
            size = size
        ))
    }
}