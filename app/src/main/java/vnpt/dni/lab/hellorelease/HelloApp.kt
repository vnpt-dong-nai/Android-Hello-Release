package vnpt.dni.lab.hellorelease

import android.app.Application

class HelloApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Logger.impl = LoggerImpl()
    }
}