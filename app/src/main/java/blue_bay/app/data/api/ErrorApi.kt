package blue_bay.app.data.api

import com.google.gson.annotations.SerializedName

class ErrorApi(
    @SerializedName("code")
    val code: Int,

    @SerializedName("message")
    val message: String

)