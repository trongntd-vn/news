package com.ntdtrong.news.features.home.adapter

import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ntdtrong.news.data.extension.displayDateTime
import com.ntdtrong.news.data.model.Article
import com.ntdtrong.news.databinding.ItemArticleBinding

class ArticleItemViewHolder(private val binding: ItemArticleBinding): RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: Article) {
        itemView.tag = data
        Glide.with(binding.ivPhoto)
            .load(data.urlToImage)
            .into(binding.ivPhoto)

        binding.tvTitle.text = data.title
        binding.tvDesc.text = HtmlCompat.fromHtml(data.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.tvTime.text = data.publishedAt.displayDateTime
    }
}