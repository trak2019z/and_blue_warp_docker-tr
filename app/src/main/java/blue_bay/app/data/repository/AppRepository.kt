package blue_bay.app.data.repository


import blue_bay.app.data.api.article.Article
import blue_bay.app.data.api.article.ArticleRequest
import blue_bay.app.data.api.base.BaseListRequest
import blue_bay.app.data.api.base.BaseListResponse
import io.reactivex.Single
import blue_bay.app.data.api.sign_in.login.LoginRequest
import blue_bay.app.data.api.sign_in.login.LoginResponse
import blue_bay.app.data.api.sign_in.register.RegisterRequest
import blue_bay.app.data.api.sign_in.register.RegisterResponse
import blue_bay.app.data.api.tags.Tag

interface AppRepository {
    fun getArticles(request: BaseListRequest) : Single<BaseListResponse<Article>>

    fun getTags(request: BaseListRequest) : Single<BaseListResponse<Tag>>
}