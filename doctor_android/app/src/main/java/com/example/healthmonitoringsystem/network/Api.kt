package com.example.healthmonitoringsystem.network

import com.example.healthmonitoringsystem.models.DefResp
import com.example.healthmonitoringsystem.models.DocProfileResp
import com.example.healthmonitoringsystem.models.PatientResp
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("username") username:String,
        @Field("password") password:String,
    ): Call<DefResp>

    @GET("profile")
    fun getDocProfile(): Call<DocProfileResp>

    @GET("/patients")
    fun getPatients(): Call<List<PatientResp>>
}
