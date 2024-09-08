package com.mangotestworkchat.app.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://plannerok.ru/"
const val CONNECTION_TIME_OUT = 5000L

class Network {

    private fun addHttpInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
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