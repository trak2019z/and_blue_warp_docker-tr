package blue_bay.app.features.menu.reset_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import blue_bay.app.databinding.FragmentResendPasswordBinding
import blue_bay.app.di.Injectable
import blue_bay.app.di.ViewModelInjectionFactory
import blue_bay.app.features.menu.SignInMenuViewModel
import blue_bay.app.utils.AutoClearedValue
import javax.inject.Inject

class ResetPasswordFragment : Fragment(), Injectable{
    private lateinit var mBinding: AutoClearedValue<FragmentResendPasswordBinding>

    @Inject
    lateinit var mainViewModelInjectionFactory: ViewModelInjectionFactory<SignInMenuViewModel>

    private val mViewModel: SignInMenuViewModel by lazy {
        ViewModelProviders.of(activity!!, mainViewModelInjectionFactory)
            .get(SignInMenuViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = AutoClearedValue(
            this,
            FragmentResendPasswordBinding.inflate(inflater, container, false)
        )

        mViewModel.loginForm.reset()

        mBinding.get().viewModel = mViewModel
        mBinding.get().btnResetPasswordStart.setOnClickListener {
            mViewModel.resetPassword()
        }

        return mBinding.get().root
    }

}