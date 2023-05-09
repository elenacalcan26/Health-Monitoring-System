package com.example.healthmonitoringsystem.models

data class PatientDetailsResp(
    val age: Int,
    val birth_date: String,
    val device_id: String,
    val full_name: String,
    val gender: String,
    val monitoring_start_date: String
)
