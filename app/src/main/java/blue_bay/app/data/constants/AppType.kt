package blue_bay.app.data.constants

import blue_bay.app.BuildConfig
import java.lang.Exception

class AppType{
    companion object {
        private const val STORE = 1
        private const val DISTRIBUTOR = 2
        private const val SALESREP = 3

        fun getAppType() = when(BuildConfig.ACCOUNT_TYPE){
            STORE -> "STORE"
            DISTRIBUTOR -> "DISTRIBUTOR"
            SALESREP -> "SALES_REP"
            else -> throw Exception("Unsupported app type")
        }
    }
}