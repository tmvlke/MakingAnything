package wskim.corefeature.network

import wskim.baselibrary.proguard_safe_zone.network.response.BaseResponse
import kotlinx.coroutines.Deferred
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLProtocolException

class NetworkRequestCoroutine<T> {
    suspend fun execute(request: Deferred<BaseResponse<T>>): NetworkResult<T> = try {
        RequestHandler<T>().handleResponse(request.await())
    } catch (e: HttpException){
        wskim.corefeature.BaseLog.e(throwable = e)
        val a = BaseResponse<T>().apply {
            errorType = "HttpException"
            message = e.fillInStackTrace().localizedMessage as String
        }
        RequestHandler<T>().handleResponse(a)
    } catch (e: UnknownHostException){
        wskim.corefeature.BaseLog.e(throwable = e)
        val a = BaseResponse<T>().apply {
            errorType = "UnknownHostException"
            message = e.fillInStackTrace().localizedMessage as String
        }
        RequestHandler<T>().handleResponse(a)
    } catch (e: SocketTimeoutException) {
        wskim.corefeature.BaseLog.e(throwable = e)
        val a = BaseResponse<T>().apply {
            errorType = "SocketTimeoutException"
            message = e.fillInStackTrace().localizedMessage as String
        }
        RequestHandler<T>().handleResponse(a)
    } catch (e: SSLProtocolException) {
        wskim.corefeature.BaseLog.e(throwable = e)
        val a = BaseResponse<T>().apply {
            errorType = "SSLProtocolException"
            message = e.fillInStackTrace().localizedMessage as String
        }
        RequestHandler<T>().handleResponse(a)
    } catch (e: Exception) {
        wskim.corefeature.BaseLog.e(throwable = e)
        val a = BaseResponse<T>().apply {
            errorType = "Exception"
            message = e.fillInStackTrace().localizedMessage as String
        }
        RequestHandler<T>().handleResponse(a)
    }
}