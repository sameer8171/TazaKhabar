package com.example.tazakhabar.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tazakhabar.R
import com.example.tazakhabar.databinding.NewsListBinding
import com.example.tazakhabar.models.Article
import com.example.tazakhabar.models.NewsResponse

class NewsAdapter(private var itemList: List<Article>, private val context: Context,private val action:(Any) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: NewsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: Article) {
           binding.tvAuthor.text = data.title
            binding.tvContent.text = data.content
            Glide.with(context)
                .load(data.urlToImage)
                .placeholder(R.drawable.newspaper)
                .into(binding.imgNews)

            binding.root.setOnClickListener {
                 action(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NewsListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Article>) {
        itemList = newList
        notifyDataSetChanged()
    }
}
