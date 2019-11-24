package blue_bay.app.data.api.tags

import blue_bay.app.data.api.base.BaseIDModel
import com.google.gson.annotations.SerializedName

class Tag (
    @SerializedName("name")
    val name : String
) : BaseIDModel()