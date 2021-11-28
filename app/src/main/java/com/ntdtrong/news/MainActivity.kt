package com.ntdtrong.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ntdtrong.news.databinding.ActivityMainBinding
import com.ntdtrong.news.features.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_content, HomeFragment())
        transaction.commit()
    }
}