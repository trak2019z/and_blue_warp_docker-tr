package blue_bay.app.features.menu.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import blue_bay.app.R
import blue_bay.app.databinding.FragmentSignInBinding
import blue_bay.app.di.Injectable
import blue_bay.app.di.ViewModelInjectionFactory
import blue_bay.app.features.menu.SignInMenuViewModel
import blue_bay.app.utils.AutoClearedValue
import javax.inject.Inject

class LoginFragment : Fragment(), Injectable{

    private lateinit var mBinding : AutoClearedValue<FragmentSignInBinding>

    @Inject
    lateinit var mainViewModelInjectionFactory: ViewModelInjectionFactory<SignInMenuViewModel>

    private val mViewModel: SignInMenuViewModel by lazy {
        ViewModelProviders.of(activity!!, mainViewModelInjectionFactory)
            .get(SignInMenuViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = AutoClearedValue(this,
            FragmentSignInBinding.inflate(inflater, container, false))

        mBinding.get().viewModel = mViewModel

        mViewModel.loginForm.reset()
        mViewModel.passwordInput.set("Qwerty12")
        mViewModel.loginInput.set("goracykapsel16@gmail.com")

        setUIAction()
        return mBinding.get().root
    }

    private fun setUIAction(){
        mBinding.get().btnLoginMenuLogin.setOnClickListener {
            mViewModel.signIn()
        }

        mBinding.get().tvForgotPassword.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_resendPasswordFragment)
        }
    }
}