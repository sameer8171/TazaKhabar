package com.example.tazakhabar.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object Constants {
    const val BASE_URL = "https://newsapi.org/"
    const val KEY = "2dc7c410dffd4bc4a33e7636fe51b2f0"
    const val key = "https://newsapi.org/v2/top-headlines?country=in&apiKey=2dc7c410dffd4bc4a33e7636fe51b2f0"

     fun isInternetAvailable(context: Context): Boolean {
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return this.getNetworkCapabilities(this.activeNetwork)?.hasCapability(
                    NetworkCapabilities.NET_CAPABILITY_INTERNET
                ) ?: false
            } else {
                (@Suppress("DEPRECATION")
                return this.activeNetworkInfo?.isConnected ?: false)
            }
        }
    }

}

enum class NewsType(val type:String){
    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology")

}

