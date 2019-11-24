package blue_bay.app.features.menu.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import blue_bay.app.BuildConfig
import blue_bay.app.R
import blue_bay.app.databinding.FragmentSignInMenuBinding
import blue_bay.app.di.Injectable
import blue_bay.app.di.ViewModelInjectionFactory
import blue_bay.app.features.menu.SignInMenuViewModel
import blue_bay.app.features.register.RegisterActivity
import blue_bay.app.utils.AutoClearedValue
import javax.inject.Inject

class SignInMenuFragment : Fragment(), Injectable {

    private lateinit var mBinding: AutoClearedValue<FragmentSignInMenuBinding>

    @Inject
    lateinit var mainViewModelInjectionFactory: ViewModelInjectionFactory<SignInMenuViewModel>

    private val mViewModel: SignInMenuViewModel by lazy {
        ViewModelProviders.of(activity!!, mainViewModelInjectionFactory)
            .get(SignInMenuViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = AutoClearedValue(
            this,
            FragmentSignInMenuBinding.inflate(inflater, container, false)
        )

        mBinding.get().tvAccountType.setText(
            when(BuildConfig.ACCOUNT_TYPE){
                1 -> R.string.account_type_store
                2 -> R.string.account_type_sales_rep
                else -> R.string.account_type_distributor
            }
        )
        initUIActions()
        return mBinding.get().root
    }


    private fun initUIActions() {
        mBinding.get().btnLoginMenuLogin.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_signInMenuFragment_to_loginFragment)
        }

        mBinding.get().btnLoginMenuRegister.setOnClickListener {
            startActivity(Intent(context!!, RegisterActivity::class.java))
        }

        mBinding.get()
    }
}