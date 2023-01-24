package com.example.tazakhabar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tazakhabar.adapter.TabAdapter
import com.example.tazakhabar.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

    }


    private fun initView() {
        val viewPager = binding.viewPager
        viewPager.adapter = TabAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(viewPager)
    }




}