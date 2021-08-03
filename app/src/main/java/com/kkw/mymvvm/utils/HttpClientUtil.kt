package com.kkw.mymvvm.utils

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object HttpClientUtil {

    private val TAG = "HttpClientUtil"

    private var okHttpClient: OkHttpClient? = null

    fun HttpClient():OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(getLogInterceptor()) //网络日志

        return builder.build()
    }

    private fun getLogInterceptor(): HttpLoggingInterceptor {
        val logger: HttpLoggingInterceptor.Logger =
            HttpLoggingInterceptor.Logger { message -> Log.i(TAG, message) }
        val interceptor = HttpLoggingInterceptor(logger)
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}