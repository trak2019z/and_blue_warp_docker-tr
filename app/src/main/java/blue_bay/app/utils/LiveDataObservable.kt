package blue_bay.app.utils

import androidx.lifecycle.LifecycleOwner

interface LiveDataObservable<out T> {

    fun observe(owner: LifecycleOwner, observer: (T) -> Unit)

    fun observeForever(observer: (T) -> Unit)
}