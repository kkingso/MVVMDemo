package com.kkw.mymvvm.logic.network

import android.util.Log
import com.kkw.mymvvm.utils.HttpClientUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private const val BASE_URL =
        "https://ss.zhaodao88.com/yemai/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(HttpClientUtil.HttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)

}