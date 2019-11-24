package blue_bay.app.features.common

import android.content.Context
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import blue_bay.app.R
import blue_bay.app.data.api.base.BaseListResponse
import blue_bay.app.data.api.tags.Tag
import io.reactivex.Observable
import io.reactivex.Single


class BaseAutocompleteAdapter(
        private val mContext: Context,
        private val callback: (query: String) -> Single<BaseListResponse<Tag>>
) : ArrayAdapter<Tag>(mContext, R.layout.list_item_tag, R.id.tv_tag_autocomplete_name),
        Filterable {

    private var mResultList = ArrayList<Tag>()
    var currentQuery: String? = null
    private var mHandler = Handler()

    override fun getCount(): Int {
        return mResultList.size
    }

    override fun getItem(position: Int): Tag {
        return mResultList[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val row = super.getView(position, convertView, parent)
        val item = getItem(position)

        val textView1 = row.findViewById(R.id.tv_tag_autocomplete_name) as TextView
        textView1.text = item.name

        return row
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            private var mMarkerRunnable = Thread {
                if (currentQuery != null) {
                    filter.filter(currentQuery)
                }
            }

            override fun performFiltering(constraint: CharSequence?): Filter.FilterResults {
                val result = FilterResults()
                if (constraint != null && currentQuery != constraint.toString()) {
                    currentQuery = constraint.toString()
                    mHandler.removeCallbacks(mMarkerRunnable)
                    mHandler.postDelayed(mMarkerRunnable, 400)
                } else {
                    mResultList = ArrayList(callback(currentQuery!!).blockingGet().list)
                }
                result.values = mResultList
                result.count = mResultList.size
                return result
            }

            override fun publishResults(constraint: CharSequence?, results: Filter.FilterResults?) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged()
                }
            }

            override fun convertResultToString(resultValue: Any): CharSequence {
                return if (resultValue is Tag) {
                    resultValue.name
                } else {
                    super.convertResultToString(resultValue)
                }
            }


        }
    }

}