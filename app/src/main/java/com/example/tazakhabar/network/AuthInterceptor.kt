package com.example.tazakhabar.network

import com.example.tazakhabar.utils.Constants.KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor():Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request =chain.request().newBuilder()
        request.addHeader("Authorization","key,$KEY")
        return chain.proceed(request.build())
    }
}