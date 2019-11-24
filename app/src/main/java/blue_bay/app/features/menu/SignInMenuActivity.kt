package blue_bay.app.features.menu

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import blue_bay.app.R
import blue_bay.app.data.Resource
import blue_bay.app.di.ViewModelInjectionFactory
import blue_bay.app.features.main.MainActivity
import blue_bay.app.utils.ToastHelper
import blue_bay.app.utils.Utils
import blue_bay.app.widgets.BaseNavActivity
import javax.inject.Inject

class SignInMenuActivity : BaseNavActivity() {

    @Inject
    lateinit var viewModelInjectionFactory: ViewModelInjectionFactory<SignInMenuViewModel>

    private val mViewModel: SignInMenuViewModel by lazy {
        ViewModelProviders.of(this, viewModelInjectionFactory)
            .get(SignInMenuViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getNavController().setGraph(R.navigation.navigation_sign_in_menu)

        mViewModel.faqLiveData.observe(this) {
            mBinding.loadingState.resource = it.step
            if (it.step is Resource.Success)
                when ((it.step as Resource.Success<SignInMenuOptions>).data) {
                    SignInMenuOptions.LoginEmail -> {
                        ToastHelper.showSuccess(this, R.string.login_success)
                        Utils.postToastDelayed{
                            val intent = Intent(this, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(intent)
                        }
                    }
                    SignInMenuOptions.ResetPassword -> {
                        Utils.postToastDelayed {
                            ToastHelper.showSuccess(this, R.string.reset_password_success)
                            onBackPressed()
                        }
                    }
                }
            else if (it.step is Resource.Error) {
                ToastHelper.showError(this, (it.step as Resource.Error).errorResponse.message)
            } else if (it.step !is Resource.Empty && it.step !is Resource.Loading)
                mViewModel
                    .state = mViewModel.state.copy(step = Resource.Empty)
        }

        mViewModel.errorLiveData.observe(this, Observer {
            ToastHelper.showBaseError(this)
        })

    }
}