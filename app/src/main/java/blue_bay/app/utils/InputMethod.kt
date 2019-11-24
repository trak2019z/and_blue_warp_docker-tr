package blue_bay.app.utils

import android.text.InputType

interface InputMethod {
    companion object {
        const val EMAIL = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        const val PASSWORD = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        const val TEXT = InputType.TYPE_CLASS_TEXT
        const val MULTILINE = InputType.TYPE_TEXT_FLAG_MULTI_LINE
        const val PHONE = InputType.TYPE_CLASS_PHONE or InputType.TYPE_NUMBER_FLAG_SIGNED
        const val NONE = InputType.TYPE_NULL
    }
}