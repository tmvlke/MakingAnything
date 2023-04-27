package wskim.corefeature.network.retrofit

import wskim.baselibrary.proguard_safe_zone.network.response.BaseResponse
import wskim.baselibrary.proguard_safe_zone.network.vo.KakaoImageVO
import wskim.baselibrary.proguard_safe_zone.network.vo.KakaoVclipVO
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface ServerAPI {

    // 이미지 검색 API
    // https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-image
    @GET("$V2_SEARCH/image")
    fun selectKakaoImageListAsync(
        @Header(HEADER_ACCESS_KEY) token: String = HEADER_ACCESS_VALUE,
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10,
        @Query("sort") sort: String = "recency"
    ): Deferred<BaseResponse<KakaoImageVO>>

    // 동영상 검색하기
    // https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-video
    @GET("$V2_SEARCH/vclip")
    fun selectKakaoVclipListAsync(
        @Header(HEADER_ACCESS_KEY) token: String = HEADER_ACCESS_VALUE,
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10,
        @Query("sort") sort: String = "recency"
    ): Deferred<BaseResponse<KakaoVclipVO>>

    companion object {
        const val HEADER_ACCESS_KEY = "Authorization"
        const val HEADER_ACCESS_VALUE = "KakaoAK 7131776afdeb98f6e7ab6830d9b7b178"
        const val V2_SEARCH = "/v2/search"
    }
}
