package blue_bay.app.data.api.sign_in.register

import com.google.gson.annotations.SerializedName

class RegisterResponse(
    @SerializedName("access_token")
    val token : String,

    @SerializedName("login")
    val login : Boolean?,

    @SerializedName("token_type")
    val tokenType : String?
)