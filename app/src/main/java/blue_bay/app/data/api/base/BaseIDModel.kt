package blue_bay.app.data.api.base

import com.google.gson.annotations.SerializedName

abstract class BaseIDModel (
    @SerializedName("id")
    val id : String? = null
)