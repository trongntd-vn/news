package com.ntdtrong.news.features.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ntdtrong.news.data.model.Article

class ArticleItemDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.description == newItem.description
                && oldItem.publishedAt == newItem.publishedAt
                && oldItem.urlToImage == newItem.urlToImage

    }
}