package blue_bay.app.widgets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import blue_bay.app.databinding.ActivityBaseNavigationBinding
import blue_bay.app.R
import javax.inject.Inject

abstract class BaseNavActivity : AppCompatActivity(), HasSupportFragmentInjector{
    protected lateinit var mBinding : ActivityBaseNavigationBinding

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_base_navigation)
    }

    protected fun getNavController() = findNavController(R.id.fragment_navigation)
}