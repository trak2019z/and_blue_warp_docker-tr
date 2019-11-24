package blue_bay.app.features.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import blue_bay.app.R
import blue_bay.app.databinding.FragmentBaseRecyclerViewBinding
import blue_bay.app.di.Injectable
import blue_bay.app.di.ViewModelInjectionFactory
import blue_bay.app.features.main.MainViewModel
import blue_bay.app.utils.AutoClearedValue
import javax.inject.Inject

class ArticlesListFragment : Fragment(), Injectable{

    @Inject
    lateinit var mainViewModelInjectionFactory: ViewModelInjectionFactory<MainViewModel>

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(activity!!, mainViewModelInjectionFactory)
            .get(MainViewModel::class.java)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val articleListBinding : AutoClearedValue<FragmentBaseRecyclerViewBinding> = AutoClearedValue(
            this, DataBindingUtil.inflate(inflater, R.layout.fragment_base_recycler_view, container, false)
        )

        initList(articleListBinding.get())

        return articleListBinding.get().root
    }

    private fun initList(articleListBinding : FragmentBaseRecyclerViewBinding){
        val articlesAdapter = ArticlesAdapter()

        viewModel.initList()

        viewModel.articlesList?.observe(this, Observer {
            articlesAdapter.submitList(it)
        })

        articleListBinding.rvBase.adapter = articlesAdapter
    }
}