package blue_bay.app.data.api.user

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("id")
    val id : Int,

    @SerializedName("name")
    val name : String,

    @SerializedName("lastName")
    val lastName : String
)