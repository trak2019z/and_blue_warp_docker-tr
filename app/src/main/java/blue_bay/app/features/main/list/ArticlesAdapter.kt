package blue_bay.app.features.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import blue_bay.app.data.api.article.Article
import blue_bay.app.databinding.ListItemArticleBinding
import blue_bay.app.utils.ListHelper

class ArticlesAdapter :
    PagedListAdapter<Article, ArticlesAdapter.ArticleItemViewHolder>(ListHelper.getDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemArticleBinding.inflate(layoutInflater, parent, false)
        return ArticleItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        val rankingItem = getItem(position)
        holder.bindTo(rankingItem!!)
    }


    class ArticleItemViewHolder(
        val binding: ListItemArticleBinding
    ) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        fun bindTo(article: Article) {
            binding.article = article
            binding.executePendingBindings()
        }
    }
}