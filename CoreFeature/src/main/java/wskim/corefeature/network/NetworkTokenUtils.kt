package wskim.corefeature.network

import android.content.Context
import wskim.baselibrary.proguard_safe_zone.exception.NoConnectToServerException

class NetworkTokenUtils {

    @Throws(NoConnectToServerException::class)
    fun serverConnectPreCheck(context : Context) : Boolean {
        // 공통 검사 시작
        // 네트워크 연결 검사
        if (!NetworkUtils.checkNetworkState(context)){
            throw NoConnectToServerException()
        }

        // 추가 검사가 필요한 경우 ...

        return true
    }
}