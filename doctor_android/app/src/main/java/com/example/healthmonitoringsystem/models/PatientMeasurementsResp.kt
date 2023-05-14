package com.example.healthmonitoringsystem.models


data class PatientMeasurementsResp(
    val measurements: Map<String, Map<String, Int>>
)
