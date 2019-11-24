package blue_bay.app.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class AutoClearedValue<T>(fragment: Fragment, var value: T?) {

    init {
        val fragmentManager = fragment.fragmentManager
        fragmentManager?.registerFragmentLifecycleCallbacks(
            object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
                    if (fragment == f) {
                        fragmentManager.unregisterFragmentLifecycleCallbacks(this)
                    }
                }
            }, false)
    }

    fun get(): T =
        value!!

    fun getNullable() : T? = value
}