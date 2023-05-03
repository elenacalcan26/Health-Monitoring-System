package com.example.healthmonitoringsystem

import com.example.healthmonitoringsystem.network.Api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val AUTH_URL = "http://10.0.2.2:8000"

    private val okHttpClient = OkHttpClient.Builder().addInterceptor {
        chain -> val original = chain.request()

        val requestBuilder = original
            .newBuilder()
            .method(original.method(), original.body())

        getAuthToken()?.let {
            token -> requestBuilder.addHeader("Authorization", token)
        }

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

    private fun getAuthToken(): String? {
        val jwtToken = HealthMonitoringSystemAndroidApp
            .instance
            .sharedPreferences
            .getString("token", "")

        if (jwtToken != null) {
            return "Bearer $jwtToken"
        }

        return null
    }
}