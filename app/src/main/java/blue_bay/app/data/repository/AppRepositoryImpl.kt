package blue_bay.app.data.repository


import android.nfc.Tag
import blue_bay.app.data.api.AppApi
import io.reactivex.Single
import blue_bay.app.data.api.AuthApi
import blue_bay.app.data.api.article.Article
import blue_bay.app.data.api.article.ArticleRequest
import blue_bay.app.data.api.base.BaseListRequest
import blue_bay.app.data.api.base.BaseListResponse
import blue_bay.app.data.api.sign_in.login.LoginRequest
import blue_bay.app.data.api.sign_in.register.RegisterRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject
constructor(private val appApi: AppApi) : AppRepository {

    override fun getTags(request: BaseListRequest) =
        Single.fromObservable(appApi.getTags(request.token, request.toMap()))

    override fun getArticles(request: BaseListRequest)=
        Single.fromObservable(appApi.getArticles(request.token, request.toMap()))


}