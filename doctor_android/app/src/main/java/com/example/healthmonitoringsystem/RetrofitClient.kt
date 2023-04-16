package com.example.healthmonitoringsystem

import com.example.healthmonitoringsystem.network.Api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import android.util.Base64
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val AUTH = "Basic " +  Base64.encodeToString("test:test".toByteArray(), Base64.NO_WRAP) // need to change
    private const val AUTH_URL = "http://10.0.2.2:5000"

    private val okHttpClient = OkHttpClient.Builder().addInterceptor {
        chain -> val original = chain.request()

        val requestBuilder = original
            .newBuilder()
//            .addHeader("Authorization", AUTH) // here auth value
            .method(original.method(), original.body())

        val request = requestBuilder.build()
        chain.proceed(request)
    }.build()

    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(AUTH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(Api::class.java)
    }
}