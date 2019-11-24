package blue_bay.app.data.api.sign_in.login

import com.google.gson.annotations.SerializedName

class LoginRequest(
    @SerializedName("email")
    val email : String,

    @SerializedName("password")
    val password : String

)