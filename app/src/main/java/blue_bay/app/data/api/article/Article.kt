package blue_bay.app.data.api.article

import blue_bay.app.data.api.base.BaseIDModel
import blue_bay.app.data.api.tags.Tag
import blue_bay.app.data.api.user.User
import com.google.gson.annotations.SerializedName

class Article(
    @SerializedName("title")
    val title : String,

    @SerializedName("content")
    val content : String,

    @SerializedName("tags")
    val tags : ArrayList<Tag>,

    @SerializedName("author")
    val author : User
) : BaseIDModel()