package blue_bay.app.data.repository


import io.reactivex.Single
import blue_bay.app.data.api.sign_in.login.LoginRequest
import blue_bay.app.data.api.sign_in.login.LoginResponse
import blue_bay.app.data.api.sign_in.register.RegisterRequest
import blue_bay.app.data.api.sign_in.register.RegisterResponse

interface AuthRepository {

    fun login(request: LoginRequest) : Single<LoginResponse>

    fun register(request: RegisterRequest) : Single<RegisterResponse>

}