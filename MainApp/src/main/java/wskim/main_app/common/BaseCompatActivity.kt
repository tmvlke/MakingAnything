package wskim.main_app.common

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseCompatActivity : AppCompatActivity() {

    protected val TAG: String = javaClass.simpleName

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if(intent.extras != null){
            outState.putAll(intent.extras)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState != null){
            intent.extras?.putAll(savedInstanceState)
        }

        onBackPressing()
    }


    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {}

        override fun onLost(network: Network) {
//            showNetworkNeedDialog()
        }
    }

//    private var networkNeedDialog : CommonDialog? = null
//    private fun showNetworkNeedDialog(){
//        networkNeedDialog = CommonDialog(this).apply {
//            setMessage(getString(R.string.network_not_found_content))
//            setOKText(getString(R.string.network_not_found_ok_text))
//            setOKTextColorToRed()
//            setCancelable(false)
//            setOnOKClickedListener(object: CommonDialog.CommonDialogOKClickedListener {
//                override fun onOKClicked() {
//                    // OK 버튼 눌렀을 경우 실행
//                    if (!NetworkUtils.checkNetworkState(this@BaseCompatActivity)) {
////                        lifecycleScope.launch(Dispatchers.Main) {
////                            delay(300)
////                            showNetworkNeedDialog()
////                        }
//                        showNetworkNeedDialog()
//                    }
//                }
//            })
//        }
//        networkNeedDialog!!.show()
//    }

    private fun registerNetworkCallback() {
        val cm = getSystemService(ConnectivityManager::class.java)
        val wifiNetworkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
        cm.registerNetworkCallback(wifiNetworkRequest, networkCallback)
    }

    private fun unregisterNetworkCallback() {
        val cm = getSystemService(ConnectivityManager::class.java)
        cm.unregisterNetworkCallback(networkCallback)
    }

    abstract fun onBackButtonClickListener()

    override fun onResume() {
        super.onResume()
        registerNetworkCallback()
    }

    override fun onPause() {
        super.onPause()
        unregisterNetworkCallback()
    }

    override fun onDestroy() {
//        if (networkNeedDialog != null) {
//            networkNeedDialog!!.dismiss()
//            networkNeedDialog = null
//        }

        super.onDestroy()
    }


//    fun finishActivityRightToLeft() {
//        finish()
//        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
//    }
//
//    fun finishActivityTopToBottom() {
//        finish()
//        overridePendingTransition(R.anim.anim_none, R.anim.slide_out_bottom)
//    }

    private fun onBackPressing(){
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // back button pressed... finishing the activity
                    onBackButtonClickListener()
                }
            })
    }
}
