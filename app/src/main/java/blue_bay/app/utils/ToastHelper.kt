package blue_bay.app.utils

import android.content.Context
import android.widget.Toast
import es.dmoral.toasty.Toasty
import blue_bay.app.R

class ToastHelper {
    companion object {
        fun showError(context: Context?, text: String?) {
            if(context == null || text == null) return
            Toasty.error(context, text, Toast.LENGTH_SHORT, true).show()
        }


        fun showError(context: Context?, text: Int) =
            showError(context, context?.getString(text))


        fun showBaseError(context: Context?) =
                showError(context, R.string.error_base)

        fun showSuccess(context: Context?, text: String?){
            if(context == null || text == null) return
            Toasty.success(context, text, Toast.LENGTH_SHORT, true).show()
        }

        fun showSuccess(context: Context?, text: Int) =
                showSuccess(context, context?.getString(text))
    }
}