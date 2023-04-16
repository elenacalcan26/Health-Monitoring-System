package com.example.healthmonitoringsystem.network

import com.example.healthmonitoringsystem.models.DefResp
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("username") username:String,
        @Field("password") password:String,
    ): Call<DefResp>
}