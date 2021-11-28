package com.ntdtrong.news.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.fragment.app.Fragment
import com.ntdtrong.news.databinding.FragmentDetailBinding
import com.ntdtrong.news.helper.NetworkHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val KEY_DETAIL_URL = "KEY_DETAIL_URL"

@AndroidEntryPoint
class DetailFragment: Fragment() {
    @Inject
    lateinit var networkHelper: NetworkHelper
    private lateinit var binding: FragmentDetailBinding
    private lateinit var url: String

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
        binding.webViewArticle.loadUrl(url)
    }

    private fun setupWebView() {
        if (networkHelper.isNetworkConnected()) {
            binding.webViewArticle.settings.cacheMode = WebSettings.LOAD_DEFAULT
        } else {
            binding.webViewArticle.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }
    }
}