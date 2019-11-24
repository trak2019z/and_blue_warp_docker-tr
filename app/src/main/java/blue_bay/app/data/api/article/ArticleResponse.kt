package blue_bay.app.data.api.article

import com.google.gson.annotations.SerializedName

class ArticleResponse(
    @SerializedName("content")
    val articlesList : ArrayList<Article>
)