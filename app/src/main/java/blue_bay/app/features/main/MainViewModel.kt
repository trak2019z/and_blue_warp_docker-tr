package blue_bay.app.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import blue_bay.app.data.Resource
import blue_bay.app.data.api.article.Article
import blue_bay.app.data.api.base.BaseListRequest
import blue_bay.app.data.api.base.BaseListResponse
import blue_bay.app.data.api.tags.Tag
import blue_bay.app.data.repository.AppRepository
import io.reactivex.disposables.CompositeDisposable
import blue_bay.app.data.repository.AuthRepository
import blue_bay.app.data.repository.UserRepository
import blue_bay.app.utils.LiveDataDelegate
import blue_bay.app.utils.SingleLiveEvent
import blue_bay.app.utils.Utils
import blue_bay.app.widgets.list.BaseListSourceFactory
import io.reactivex.Single
import pl.tracker.app.widgets.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val userRepository: UserRepository,
                                        private val appRepository: AppRepository
)
    : BaseViewModel<MainOptions>(){

    val mainLiveData = LiveDataDelegate(MainState())

    var articlesList: LiveData<PagedList<Article>>? = null
    private var articlesListFactory: BaseListSourceFactory<Article>? = null

    var currentTagList = ArrayList<Tag>()

    fun initList() {

        state = state.copy(step = Resource.Loading)

        articlesListFactory = BaseListSourceFactory(
            BaseListRequest(token = userRepository.getToken()), disposable,this
        ) { request -> appRepository.getArticles(request) }

        articlesList = LivePagedListBuilder<Int, Article>(
            articlesListFactory!!,
            Utils.getDefaultPagedListConfig()
        ).build()

    }

    fun getAutocompleteTags(query : String) : Single<BaseListResponse<Tag>>{
        return appRepository.getTags(BaseListRequest(token = userRepository.getToken(), query = query))
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()

    }
}