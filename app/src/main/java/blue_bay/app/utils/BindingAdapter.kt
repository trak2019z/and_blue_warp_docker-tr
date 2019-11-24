package blue_bay.app.utils

import android.view.View
import androidx.databinding.BindingAdapter
import blue_bay.app.data.Resource

@BindingAdapter("visibleWhileLoading")
fun bindVisibleWhileLoading(t: View, resource: Resource<*>?) {
    t.visibility = if (resource is Resource.Loading) View.VISIBLE else View.GONE
}

@BindingAdapter("visibility")
fun bindVisibility(view : View, visibility: Boolean?){
    view.visibility = if(visibility == true) View.VISIBLE else View.GONE
}