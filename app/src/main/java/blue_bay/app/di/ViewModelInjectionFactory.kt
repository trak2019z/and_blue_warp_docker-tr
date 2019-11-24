package blue_bay.app.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelInjectionFactory<VM : ViewModel> @Inject constructor(
        private val viewModel: dagger.Lazy<VM>) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            viewModel.get() as T
}