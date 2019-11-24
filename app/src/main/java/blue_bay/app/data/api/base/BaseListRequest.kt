package blue_bay.app.data.api.base

import blue_bay.app.utils.Constants

class BaseListRequest (
    var page : Int = 0,

    val size : Int = Constants.LIST_BASE_LIMIT,

    val token : String,

    val query : String? = null
) {
    fun toMap() : HashMap<String, String> {
        val map = hashMapOf("size" to size.toString(),
            "page" to page.toString())
        query?.let { map["search"] = it }
        return map
    }
}