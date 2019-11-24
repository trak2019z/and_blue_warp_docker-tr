package blue_bay.app.utils

import android.os.Handler
import androidx.paging.PagedList


class Utils {
    companion object {
        fun postToastDelayed(method : () -> Unit){
            Handler().postDelayed({method()}, 500)
        }

        fun getDefaultPagedListConfig() = PagedList.Config.Builder()
            .setPageSize(Constants.LIST_BASE_LIMIT)
            .setInitialLoadSizeHint(Constants.LIST_BASE_LIMIT)
            .setEnablePlaceholders(false)
            .build()
    }
}