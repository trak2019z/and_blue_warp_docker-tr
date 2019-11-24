package pl.tracker.app.widgets

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import blue_bay.app.data.Resource
import blue_bay.app.utils.LiveDataDelegate
import blue_bay.app.utils.SingleLiveEvent
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*

data class BaseState<T : Any>(
    var step: Resource<T> = Resource.Empty
)


open class BaseViewModel<T : Any> : ViewModel(), BaseViewModelListener{

    val disposable = CompositeDisposable()

    val baseLiveData = LiveDataDelegate(BaseState<T>())
    var state by baseLiveData

    val errorLiveEvent = SingleLiveEvent<Throwable>()

    override fun setLoading(){
        state = state.copy(step = Resource.Loading)
    }

    override fun setError(error: Throwable) {
        disposable.add(
        Single.just{true}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ state = state.copy(step = Resource.Error(error)) },{}))


    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}

interface BaseViewModelListener{
    fun setLoading()
    fun setError(error : Throwable)
}