package com.example.traderevchallenge.Utils.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiHandler {

    private const val HTTP_TIMEOUT: Long = 30000
    private var apiService: ApiService? = null
    private const val BaseURL = "https://api.unsplash.com"
    internal const val ACCESS_TOKEN="7c6ad8ec563b00adbeb5080a2bd3e4943aaf882ba0cd5fb63da8e22b0bdca3c5"

    fun getApiService(): ApiService {
        if (apiService == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .readTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(client)
                .build()

            apiService = retrofit.create(ApiService::class.java)
            return apiService as ApiService
        } else {
            return apiService as ApiService
        }
    }
}
