package com.example.tazakhabar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import com.example.tazakhabar.databinding.ActivityBrowserBinding
import com.example.tazakhabar.models.Article
import com.google.gson.Gson

class BrowserActivity : AppCompatActivity() {
    private lateinit var binding:ActivityBrowserBinding
    private  var data:Article? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrowserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        data = Gson().fromJson(intent.getStringExtra("data"),Article::class.java)

        initView()


    }

    private fun initView() {

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBar.isVisible = false
                binding.webView.isVisible = true
            }
        }
        try {
            data?.url?.let { binding.webView.loadUrl(it) }
        }catch (_:Exception){}

        binding.navbar.apply {
            btnBack.isVisible = true
            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}