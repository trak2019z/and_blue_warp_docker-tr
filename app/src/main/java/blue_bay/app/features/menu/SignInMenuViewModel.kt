package blue_bay.app.features.menu

import androidx.lifecycle.ViewModel
import com.mlykotom.valifi.ValiFiForm
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import blue_bay.app.R
import blue_bay.app.data.Resource
import blue_bay.app.data.api.sign_in.login.LoginRequest
import blue_bay.app.data.repository.AuthRepository
import blue_bay.app.data.repository.UserRepository
import blue_bay.app.utils.LiveDataDelegate
import blue_bay.app.utils.SingleLiveEvent
import blue_bay.app.utils.ValiFieldEmailPlaceholder
import blue_bay.app.utils.ValiFieldPlaceholder
import javax.inject.Inject

class SignInMenuViewModel @Inject constructor(private val userRepository: UserRepository,
                                              private val authRepository: AuthRepository): ViewModel(){

    val loginInput = ValiFieldEmailPlaceholder().addPlaceholderText(R.string.email_label)
    val passwordInput = ValiFieldPlaceholder().addMinLengthValidator(R.string.password_label, 6)

    val loginForm = ValiFiForm(loginInput, passwordInput)

    val faqLiveData = LiveDataDelegate(SignInMenuState())
    var state by faqLiveData
    private val disposable = CompositeDisposable()

    val errorLiveData = SingleLiveEvent<Void>()

    fun signIn() {
        state = state.copy(step = Resource.Loading)

        if(!loginForm.isValid){
            errorLiveData.call()
            state = state.copy(Resource.Empty)
            return
        }
        disposable.add(authRepository.login(LoginRequest(loginInput.value!!, passwordInput.value!!))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userRepository.setToken(it.token)
                state = state.copy(step = Resource.Success(SignInMenuOptions.LoginEmail))
            }, {
                state = state.copy(step = Resource.Error(it))
            }))
    }

    fun resetPassword() {
        state = state.copy(step = Resource.Loading)

        if(!loginInput.isValid){
            errorLiveData.call()
            state = state.copy(Resource.Empty)
            return
        }

        disposable.add(authRepository.login(LoginRequest(loginInput.value!!, passwordInput.value!!))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                state = state.copy(step = Resource.Success(SignInMenuOptions.ResetPassword))
            }, {
                state = state.copy(step = Resource.Error(it))
            }))
    }

    override fun onCleared() {
        loginForm.destroy()
        disposable.clear()
        super.onCleared()

    }
}