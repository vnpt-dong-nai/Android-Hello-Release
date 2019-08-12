package vnpt.dni.lab.hellorelease

interface LoggerImplementationInterface {
    fun d(tag:String, msg:String)
}

class Logger {
    companion object {
        var impl : LoggerImplementationInterface? = null

        fun d(msg:String){
            impl?.let {
                it.d("Logger", msg)
            }
        }
    }
}