package wskim.data.network.manager

import android.content.Context
import wskim.data.network.NetworkTokenUtils

abstract class BaseNetworkManager(private val context: Context) {

    private val networkTokenUtils : NetworkTokenUtils = NetworkTokenUtils()
    protected fun preTreatment() : Boolean {
        return networkTokenUtils.serverConnectPreCheck(context)
    }
}