package blue_bay.app.utils

import androidx.recyclerview.widget.DiffUtil
import blue_bay.app.data.api.base.BaseIDModel

class ListHelper {
    companion object {

        fun <T : BaseIDModel> getDiffCallback() =
            object : DiffUtil.ItemCallback<T>() {
                override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
                    return oldItem == newItem
                }
            }
    }
}