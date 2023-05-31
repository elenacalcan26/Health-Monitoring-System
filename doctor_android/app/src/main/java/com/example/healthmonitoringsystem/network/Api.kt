package com.example.healthmonitoringsystem.network

import com.example.healthmonitoringsystem.models.*
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

    @GET("/patients/{id}/measurements")
    fun getPatientMeasurements(
        @Path("id") patientId: Int
    ): Call<PatientMeasurementsResp>

    @GET("/patients/{id}/status")
    fun getPatientMedicalStatus(
        @Path("id") patientId: Int
    ): Call<PatientMedicalStatusResp>
}
