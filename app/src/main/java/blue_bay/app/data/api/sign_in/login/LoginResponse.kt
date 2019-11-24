package blue_bay.app.data.api.sign_in.login

import com.google.gson.annotations.SerializedName

class LoginResponse(
    @SerializedName("token")
    val token: String
)