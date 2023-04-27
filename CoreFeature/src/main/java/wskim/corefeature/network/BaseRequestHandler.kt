package wskim.corefeature.network

import wskim.baselibrary.proguard_safe_zone.network.response.BaseResponse
import retrofit2.Response

abstract class BaseRequestHandler<T> {

    protected fun getResponseResult(response: Any?, responseHandler: (ResponseResult) -> Unit) {
        when {
            isSuccessful(response) -> {
                responseHandler.invoke(ResponseResult(true, null, response))
            }
            else -> handleServerError(response, responseHandler)
        }

    }

    private fun isSuccessful(response: Any?): Boolean {
        return when (response) {
            is Response<*> -> response.isSuccessful && ((response.body() as? BaseResponse<*>)?.errorType?:"").isEmpty()
            is BaseResponse<*> -> response.errorType.isEmpty()
            else -> false
        }
    }

    open fun handleServerError(response: Any?, responseHandler: (ResponseResult) -> Unit) {
        val mcResponse: BaseResponse<T>? = when (response) {
            is Response<*> -> (response.body() as? BaseResponse<*>) as? BaseResponse<T>?
            is BaseResponse<*> -> response as? BaseResponse<T>?
            else -> null
        }

        mcResponse?.also {
            responseHandler.invoke(ResponseResult(false, it.errorType, null))
        }
    }

    data class ResponseResult(val isSuccess: Boolean, val errMsg: String?, val response: Any?)
}