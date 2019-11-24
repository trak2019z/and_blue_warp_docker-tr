package blue_bay.app.data.api.sign_in.register

import com.google.gson.annotations.SerializedName

class RegisterRequest(
    @SerializedName("email")
    val email : String?,

    @SerializedName("password")
    val password : String?,

    @SerializedName("role")
    val role : String
)