package blue_bay.app.features.register

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import blue_bay.app.BuildConfig
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import blue_bay.app.R
import blue_bay.app.data.Resource
import blue_bay.app.databinding.ActivityRegisterBinding
import blue_bay.app.di.ViewModelInjectionFactory
import blue_bay.app.features.main.MainActivity
import blue_bay.app.features.register.part_1.RegisterFragmentPart1
import blue_bay.app.utils.ToastHelper
import javax.inject.Inject

class RegisterActivity : AppCompatActivity(), HasSupportFragmentInjector {

    private lateinit var mBinding: ActivityRegisterBinding

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    @Inject
    lateinit var viewModelInjectionFactory: ViewModelInjectionFactory<RegisterViewModel>

    private val mViewModel: RegisterViewModel by lazy {
        ViewModelProviders.of(this, viewModelInjectionFactory)
            .get(RegisterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        mBinding.viewModel = mViewModel

        getNavController().setGraph(R.navigation.navigation_register)

        mBinding.btnRegisterBack.setOnClickListener { onBackPressed() }

        mViewModel.errorLiveData.observe(this, Observer {
            ToastHelper.showBaseError(this)
        })



        mViewModel.registerLiveData.observe(this) {
            mBinding.loadingState.resource = it.step
            if (it.step is Resource.Success) {
                when ((it.step as Resource.Success<RegisterOptions>).data) {
                    RegisterOptions.RegisterEmail -> {
                        ToastHelper.showSuccess(this, R.string.login_success)
                        Handler().postDelayed({
                            val intent = Intent(this, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(intent)
                        }, 500)
                    }
                }
                mViewModel.state = mViewModel.state.copy(step = Resource.Empty)
            } else if (it.step is Resource.Error) {
                ToastHelper.showError(this, (it.step as Resource.Error).errorResponse.message)
            }

        }

    }

    private fun getNavController() = findNavController(R.id.fragment_navigation)
}