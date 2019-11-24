package blue_bay.app.data.api

import com.google.gson.annotations.SerializedName

class ErrorResponse(
    @SerializedName("error")
    var error: String? = null,

    @SerializedName("message")
    var message: String? = null,

    @SerializedName("status")
    var status: Int? = null
)