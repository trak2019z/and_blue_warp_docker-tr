package blue_bay.app.data.api.article

class ArticleRequest(
    var page : Int,

    var size : Int = 20,

    var tags : ArrayList<String>
) {
    fun toMap() : Map<String, String>{
        return mapOf(
            "page" to page.toString(),
            "size" to size.toString()
        )
    }
}