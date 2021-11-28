package com.ntdtrong.news.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ntdtrong.news.data.model.Article
import com.ntdtrong.news.databinding.ItemArticleBinding
import java.util.*

class ArticleAdapter : ListAdapter<Article, ArticleItemViewHolder>(ArticleItemDiffCallback()) {

    fun updateList(list: List<Article>) {
        submitList(Collections.unmodifiableList(list))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ArticleItemViewHolder(ItemArticleBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }
}