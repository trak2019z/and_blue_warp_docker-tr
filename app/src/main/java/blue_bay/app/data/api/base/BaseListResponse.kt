package blue_bay.app.data.api.base

import com.google.gson.annotations.SerializedName

class BaseListResponse<T : BaseIDModel>(
    @SerializedName("content")
    val list: MutableList<T>
)