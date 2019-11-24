package blue_bay.app.widgets.list

import androidx.paging.PositionalDataSource
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import blue_bay.app.data.api.base.BaseIDModel
import blue_bay.app.data.api.base.BaseListRequest
import blue_bay.app.data.api.base.BaseListResponse
import pl.tracker.app.widgets.BaseViewModelListener

class BaseListSource<T : BaseIDModel>(
    val baseListRequest: BaseListRequest,
    private val compositeDisposable: CompositeDisposable,
    private val callback: (BaseListRequest) -> Single<BaseListResponse<T>>,
    private val baseViewModelListener: BaseViewModelListener
) : PositionalDataSource<T>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {
        baseListRequest.page++

        compositeDisposable.add(
            callback(baseListRequest)
                .subscribe({
                    callback.onResult(it.list)
                }, { baseViewModelListener.setError(it) })
        )
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
        baseListRequest.page = 0

        compositeDisposable.add(
            callback(baseListRequest)
                .subscribe({
                    callback.onResult(it.list, 0)
                }, {
                    baseViewModelListener.setError(it) })
        )
    }


}