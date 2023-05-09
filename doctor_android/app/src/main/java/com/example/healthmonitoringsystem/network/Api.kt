package com.example.healthmonitoringsystem.network

import com.example.healthmonitoringsystem.models.DefResp
import com.example.healthmonitoringsystem.models.DocProfileResp
import com.example.healthmonitoringsystem.models.PatientDetailsResp
import com.example.healthmonitoringsystem.models.PatientResp
import retrofit2.Call
import retrofit2.http.*

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

    @GET("/patients/{id}")
    fun getPatientDetails(
        @Path("id") patientId: Int
    ): Call<PatientDetailsResp>
}
