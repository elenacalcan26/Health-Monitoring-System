package com.example.healthmonitoringsystem.network

import com.example.healthmonitoringsystem.models.*
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("/auth/login")
    fun loginUser(
        @Field("username") username:String,
        @Field("password") password:String,
    ): Call<DefResp>

    @GET("/business-logic/profile")
    fun getDocProfile(): Call<DocProfileResp>

    @GET("/business-logic/patients")
    fun getPatients(): Call<List<PatientResp>>

    @GET("/business-logic/patients/{id}")
    fun getPatientDetails(
        @Path("id") patientId: Int
    ): Call<PatientDetailsResp>

    @GET("/business-logic/patients/{id}/measurements")
    fun getPatientMeasurements(
        @Path("id") patientId: Int
    ): Call<PatientMeasurementsResp>

    @GET("/business-logic/patients/{id}/status")
    fun getPatientMedicalStatus(
        @Path("id") patientId: Int
    ): Call<PatientMedicalStatusResp>
}
