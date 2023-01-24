package com.example.tazakhabar.di

import com.example.tazakhabar.network.AuthInterceptor
import com.example.tazakhabar.network.NewsApi
import com.example.tazakhabar.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(authInterceptor: AuthInterceptor):OkHttpClient{
        return OkHttpClient.Builder().addNetworkInterceptor(authInterceptor).build()

    }

//    @Provides
//    @Singleton
//    fun provideNews(retrofitBuilder:Builder,okHttpClient: OkHttpClient):NewsApi{
//         return retrofitBuilder
//             .client(okHttpClient)
//             .build()
//             .create(NewsApi::class.java)
//    }

    @Provides
    @Singleton
    fun provideNews(retroBuilder:Builder):NewsApi{
        return retroBuilder.build().create(NewsApi::class.java)

    }


}