package com.granch.network

import com.mangotestworkchat.app.network.ApiServer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "http://10.0.2.2:7402/"
const val CONNECTION_TIME_OUT = 5000L

class Network {

    private fun addHttpInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    private fun getRetrofitBuilder(): Retrofit {

        val oKHttpClient = OkHttpClient.Builder()
            .addInterceptor(addHttpInterceptor())
            .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.MILLISECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(oKHttpClient)
            .build()
    }

    fun getApiServer(): ApiServer {
        return getRetrofitBuilder().create(ApiServer::class.java)
            ?: throw java.lang.RuntimeException("Не создан класс ApiServer")
    }
}