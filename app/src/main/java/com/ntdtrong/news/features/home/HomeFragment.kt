package com.ntdtrong.news.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ntdtrong.news.R
import com.ntdtrong.news.data.model.Article
import com.ntdtrong.news.databinding.FragmentHomeBinding
import com.ntdtrong.news.features.detail.KEY_DETAIL_URL
import com.ntdtrong.news.features.home.adapter.ArticleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment(), ArticleAdapter.OnItemClickedListener {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        setupObserver()

        loadData()
    }

    override fun onArticleItemClicked(article: Article) {
        val bundle = Bundle()
        bundle.putString(KEY_DETAIL_URL, article.url)
        findNavController().navigate(R.id.action_nav_home_to_detail, bundle)
    }

    private fun loadData() {
        viewModel.getNews("bbc-sport")
        binding.tvEmptyMessage.visibility = View.GONE
    }

    private fun setupList() {
        adapter = ArticleAdapter()
        adapter.listener = this
        binding.rvNews.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        binding.rvNews.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.articles.observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                binding.tvEmptyMessage.visibility = View.VISIBLE
                binding.rvNews.visibility = View.GONE
            } else {
                binding.tvEmptyMessage.visibility = View.GONE
                binding.rvNews.visibility = View.VISIBLE
                adapter.updateList(it)
            }
        })
    }
}