package com.example.healthmonitoringsystem.entities

data class MonitoredMeasurements(
    val timestamp: String,
    val values: List<Measurement>
)
