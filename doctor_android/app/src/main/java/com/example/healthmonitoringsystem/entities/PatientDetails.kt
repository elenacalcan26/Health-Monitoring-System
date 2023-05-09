package com.example.healthmonitoringsystem.entities

data class PatientDetails(
    val full_name: String,
    val age: Int,
    val birth_date: String,
    val gender: String,
    val device_id: String,
    val monitoring_start_date: String
)