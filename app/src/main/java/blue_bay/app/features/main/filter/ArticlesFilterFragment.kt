package blue_bay.app.features.main.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import blue_bay.app.R
import blue_bay.app.databinding.FragmentArticlesFilterBinding
import blue_bay.app.databinding.FragmentBaseRecyclerViewBinding
import blue_bay.app.di.Injectable
import blue_bay.app.di.ViewModelInjectionFactory
import blue_bay.app.features.common.BaseAutocompleteAdapter
import blue_bay.app.features.main.MainViewModel
import blue_bay.app.utils.AutoClearedValue
import javax.inject.Inject

class ArticlesFilterFragment : Fragment(), Injectable{

    @Inject
    lateinit var mainViewModelInjectionFactory: ViewModelInjectionFactory<MainViewModel>

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(activity!!, mainViewModelInjectionFactory)
            .get(MainViewModel::class.java)
    }

    private lateinit var tagsAdapter: BaseAutocompleteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val articleListBinding : AutoClearedValue<FragmentArticlesFilterBinding> = AutoClearedValue(
            this, DataBindingUtil.inflate(inflater, R.layout.fragment_articles_filter, container, false)
        )

        initAutocompleteAdapter(articleListBinding.get())

        return articleListBinding.get().root
    }

    private fun initAutocompleteAdapter(articleListBinding : FragmentArticlesFilterBinding) {
        tagsAdapter = BaseAutocompleteAdapter(context!!) { viewModel.getAutocompleteTags(it) }
        articleListBinding.layoutMaterialAutocomplete.autocompleteBase.threshold = 3
        articleListBinding.layoutMaterialAutocomplete.autocompleteBase.setAdapter(tagsAdapter)

        articleListBinding.layoutMaterialAutocomplete.autocompleteBase.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    viewModel.currentTagList.add(tagsAdapter.getItem(position))
                }
    }
}