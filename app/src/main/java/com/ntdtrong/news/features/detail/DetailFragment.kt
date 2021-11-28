package com.ntdtrong.news.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ntdtrong.news.databinding.FragmentDetailBinding
import com.ntdtrong.news.features.base.SwipeToRefreshFragment
import com.ntdtrong.news.helper.NetworkHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val KEY_DETAIL_URL = "KEY_DETAIL_URL"

@AndroidEntryPoint
class DetailFragment : SwipeToRefreshFragment() {
    @Inject
    lateinit var networkHelper: NetworkHelper
    private lateinit var binding: FragmentDetailBinding
    private lateinit var url: String
    override val swipeLayout: SwipeRefreshLayout
        get() = binding.layoutSwipeDetail

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        url = arguments?.getString(KEY_DETAIL_URL) ?: return
        setupWebView()
        swipeLayout.isRefreshing = true
        binding.webViewArticle.loadUrl(url)
    }

    override fun refreshData() {
        binding.webViewArticle.reload()
    }

    private fun setupWebView() {
        with(binding.webViewArticle) {
            if (networkHelper.isNetworkConnected()) {
                settings.cacheMode = WebSettings.LOAD_DEFAULT
            } else {
                settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            }
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    swipeLayout.isRefreshing = false
                }
            }
        }
    }
}