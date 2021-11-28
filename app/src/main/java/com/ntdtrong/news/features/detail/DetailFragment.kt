package com.ntdtrong.news.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ntdtrong.news.databinding.FragmentDetailBinding

const val KEY_DETAIL_URL = "KEY_DETAIL_URL"

class DetailFragment: Fragment() {
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
        binding.webViewArticle.loadUrl(url)
    }
}