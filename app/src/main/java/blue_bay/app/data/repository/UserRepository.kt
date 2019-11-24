package blue_bay.app.data.repository

import blue_bay.app.BuildConfig

interface UserRepository {

    fun setToken(token : String)

    fun getToken() : String

    companion object {
        const val STORE = 1
        const val DISTRIBUTOR = 2
        const val SALES_REP = 3

        const val STORE_API = "STORE"
        const val DISTRIBUTOR_API = "DISTRIBUTOR"
        const val SALES_REP_API = "SALES_REP"

        fun getAccountTypeApi() = when(BuildConfig.ACCOUNT_TYPE){
            STORE -> STORE_API
            DISTRIBUTOR -> DISTRIBUTOR_API
            else -> SALES_REP_API
        }

    }
}