package wskim.main_app.core.exception

import wskim.corefeature.BaseLog


class WskimUncaughtExceptionHandler : Thread.UncaughtExceptionHandler {

    private val defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()

    override fun uncaughtException(t: Thread, e: Throwable) {
        BaseLog.e(t.name, throwable = e)

        defaultUncaughtExceptionHandler?.uncaughtException(t, e)
    }
}