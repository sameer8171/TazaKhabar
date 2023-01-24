package com.example.tazakhabar.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tazakhabar.BrowserActivity
import com.example.tazakhabar.adapter.NewsAdapter
import com.example.tazakhabar.databinding.FragmentBusinessBinding
import com.example.tazakhabar.utils.NetworkResult
import com.example.tazakhabar.utils.NewsType
import com.example.tazakhabar.viewmodel.NewsViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BusinessFragment() : Fragment() {
    private lateinit var binding: FragmentBusinessBinding
    private lateinit var adapter: NewsAdapter
    private val newsModel by viewModels<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBusinessBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observer()
    }

    private fun initView() {
        newsModel.getNews(country = "in", page = 100, category = NewsType.BUSINESS.type)
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        adapter = NewsAdapter(emptyList(), requireContext()) {
            Intent(requireContext(), BrowserActivity::class.java).also { intent ->
                intent.putExtra("data", Gson().toJson(it))
                startActivity(intent)
            }
        }
        binding.rvList.adapter = adapter
    }

    private fun observer() {
        try {
            newsModel.newsLiveData.observe(viewLifecycleOwner) { res ->
                when (res) {
                    is NetworkResult.Error -> {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    }
                    is NetworkResult.Loading -> {
                        showShimmer()
                    }
                    is NetworkResult.Success -> {
                        try {
                            hideShimmer()
                            adapter.updateList(res.data ?: emptyList())
                        } catch (_: Exception) {
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun showShimmer() {
        binding.layShimmer.isVisible = true
        binding.rvList.isVisible = false
        binding.layShimmer.startShimmer()
    }

    private fun hideShimmer() {
        binding.layShimmer.isVisible = false
        binding.rvList.isVisible = true
        binding.layShimmer.startShimmer()
    }


}