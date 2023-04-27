package wskim.corefeature.network

import wskim.baselibrary.proguard_safe_zone.exception.base.BaseWebException
import wskim.baselibrary.proguard_safe_zone.network.response.BaseResponse
import wskim.baselibrary.proguard_safe_zone.network.vo.KakaoMetaVO

class RequestHandler<T> : BaseRequestHandler<T>() {
    private lateinit var responseResult: ResponseResult

    fun handleResponse(response: BaseResponse<T>?): NetworkResult<T> {
        super.getResponseResult(response) {
            responseResult = it
            return@getResponseResult
        }

        val response = responseResult.response as? BaseResponse<T>?
        return if (responseResult.isSuccess)
            onSuccess(response?.documents, response?.meta)
        else onFailure(response?.errorType?:"", response?.message ?:"")
    }

    private fun onSuccess(documents: ArrayList<T>?, meta: KakaoMetaVO?): NetworkResult<T> {
        return NetworkResult(documents, meta)
    }

    private fun onFailure(errorType : String, handledMessage: String): NetworkResult<T> {
        return NetworkResult(
            errorType,
            BaseWebException(
                handledMessage
            )
        )
    }
}