package blue_bay.app.features.register.part_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import blue_bay.app.databinding.FragmentRegister1Binding
import blue_bay.app.di.Injectable
import blue_bay.app.di.ViewModelInjectionFactory
import blue_bay.app.features.register.RegisterViewModel
import blue_bay.app.utils.AutoClearedValue
import javax.inject.Inject

class RegisterFragmentPart1 : Fragment(), Injectable{

    private lateinit var mBinding : AutoClearedValue<FragmentRegister1Binding>

    @Inject
    lateinit var mainViewModelInjectionFactory: ViewModelInjectionFactory<RegisterViewModel>

    private val mViewModel: RegisterViewModel by lazy {
        ViewModelProviders.of(activity!!, mainViewModelInjectionFactory)
            .get(RegisterViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = AutoClearedValue(
            this,
            FragmentRegister1Binding.inflate(inflater, container, false)
        )

        mBinding.get().viewModel = mViewModel

        return mBinding.get().root
    }
}