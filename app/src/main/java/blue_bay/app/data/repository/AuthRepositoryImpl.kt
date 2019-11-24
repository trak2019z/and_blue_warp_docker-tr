package blue_bay.app.data.repository


import io.reactivex.Single
import blue_bay.app.data.api.AuthApi
import blue_bay.app.data.api.sign_in.login.LoginRequest
import blue_bay.app.data.api.sign_in.register.RegisterRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject
constructor(private val authApi: AuthApi) : AuthRepository {

    override fun login(request: LoginRequest) =
            Single.fromObservable(authApi.login(request))

    override fun register(request: RegisterRequest) =
        Single.fromObservable(authApi.register(request))


}