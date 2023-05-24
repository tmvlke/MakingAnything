package wskim.main_app.core

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Process
import dagger.hilt.android.HiltAndroidApp
import wskim.corefeature.BaseLog
import wskim.main_app.core.exception.WskimUncaughtExceptionHandler

// 마지막 프리징 버전 정보: 0.7.1.7 / 68
@HiltAndroidApp
class WskimApplication : Application() {

    companion object{
        lateinit var appContext: Context
        const val TAG = "MCApplication"
    }

    override fun onCreate() {

        if (isMainProcess(this)) {
            super.onCreate()

            BaseLog.d(TAG, "onCreate, time:" + System.currentTimeMillis())
            appContext = applicationContext

            // 오류 수집 활성화
            if (Thread.getDefaultUncaughtExceptionHandler() !== WskimUncaughtExceptionHandler())
                Thread.setDefaultUncaughtExceptionHandler(WskimUncaughtExceptionHandler())
        }
    }

    private fun isMainProcess(context: Context): Boolean {
        val pid = Process.myPid()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningAppProcessesInfo = activityManager.runningAppProcesses
        if (runningAppProcessesInfo != null) {
            for (processInfo in activityManager.runningAppProcesses) {
                if (processInfo.pid == pid)
                    return processInfo.processName == context.packageName
            }
        }
        return false
    }
}