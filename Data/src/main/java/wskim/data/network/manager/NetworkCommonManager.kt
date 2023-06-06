package wskim.data.network.manager

import android.content.Context
import wskim.data.network.NetworkRequestCoroutine
import wskim.data.network.NetworkResult
import wskim.data.network.retrofit.ServerAPI
import wskim.domain.proguard_safe_zone.network.vo.KakaoImageVO
import wskim.domain.proguard_safe_zone.network.vo.KakaoVclipVO

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