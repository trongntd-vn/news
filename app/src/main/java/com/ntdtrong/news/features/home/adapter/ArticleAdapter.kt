package com.ntdtrong.news.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ntdtrong.news.data.model.Article
import com.ntdtrong.news.databinding.ItemArticleBinding
import java.util.*

class ArticleAdapter : ListAdapter<Article, ArticleItemViewHolder>(ArticleItemDiffCallback()) {
    var listener: OnItemClickedListener? = null

    fun updateList(list: List<Article>) {
        submitList(Collections.unmodifiableList(list))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ArticleItemViewHolder(ItemArticleBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
        holder.itemView.setOnClickListener {
            val article = it.tag as Article
            listener?.onArticleItemClicked(article)
        }
    }

    interface OnItemClickedListener {
        fun onArticleItemClicked(article: Article)
    }
}