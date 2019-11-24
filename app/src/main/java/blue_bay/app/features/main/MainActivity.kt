package blue_bay.app.features.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import blue_bay.app.R
import blue_bay.app.data.Resource
import blue_bay.app.databinding.ActivityMainBinding
import blue_bay.app.di.ViewModelInjectionFactory
import blue_bay.app.features.main.filter.ArticlesFilterFragment
import blue_bay.app.features.main.list.ArticlesListFragment
import blue_bay.app.utils.ToastHelper
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelInjectionFactory: ViewModelInjectionFactory<MainViewModel>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelInjectionFactory)
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainActivityBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initViewModel(mainActivityBinding)
        initNavigation(mainActivityBinding)
    }

    private fun initViewModel(activityMainBinding: ActivityMainBinding) {
        viewModel.mainLiveData.observe(this) {
            activityMainBinding.loadingState.resource = it.step
            if (it.step is Resource.Success) {
                when ((it.step as Resource.Success<MainOptions>).data) {

                }
            } else if (it.step is Resource.Error) {
                val error = it.step as Resource.Error
                ToastHelper.showError(this, error.errorResponse.message)
            }
        }
    }

    private fun initNavigation(activityMainBinding: ActivityMainBinding) {
        findNavController(R.id.fragment_navigation).run {
            setGraph(R.navigation.navigation_main)

            addOnDestinationChangedListener { controller, destination, arguments ->
                activityMainBinding.toolbarMain.tvToolbarTitle.setText(
                    when (destination.label) {
                        ArticlesListFragment::class.java.simpleName -> R.string.articles
                        ArticlesFilterFragment::class.java.simpleName -> R.string.filter
                        else -> throw UnsupportedOperationException("Unknown road")
                    }
                )

                if(destination.label == ArticlesListFragment::class.java.simpleName){
                    activityMainBinding.toolbarMain.ivToolbarFilter.visibility = View.VISIBLE
                    activityMainBinding.toolbarMain.ivToolbarMenu.setImageResource(R.drawable.ic_hamburger)
                } else {
                    activityMainBinding.toolbarMain.ivToolbarFilter.visibility = View.GONE
                    activityMainBinding.toolbarMain.ivToolbarMenu.setImageResource(R.drawable.ic_back_arrow)
                }

                initToolbarListeners(activityMainBinding)
            }
        }
    }

    private fun initToolbarListeners(activityMainBinding: ActivityMainBinding){
        activityMainBinding.toolbarMain.ivToolbarFilter.setOnClickListener {
            findNavController(R.id.fragment_navigation).run {
                    navigate(
                        R.id.action_articlesListFragment_to_articlesFilterFragment)

            }
        }

        activityMainBinding.toolbarMain.ivToolbarMenu.setOnClickListener {
            onBackPressed()
        }
    }
}