package com.example.healthmonitoringsystem.extensions

import com.example.healthmonitoringsystem.entities.Measurement
import com.example.healthmonitoringsystem.entities.MonitoredMeasurements
import com.example.healthmonitoringsystem.models.PatientMeasurementsResp

// PatientMeasurementsResp(measurements={2023-05-12 13:00:00={BPM=107, SPO2=69}, 2023-05-12 13:40:00={BPM=187, SPO2=93}})

fun PatientMeasurementsResp.toMonitoredMeasurementsList(): List<MonitoredMeasurements> {
    return measurements.map { (timestamp, measurements) ->
        MonitoredMeasurements(
            timestamp,
            measurements.map { (type, value) -> Measurement(type, value) })
    }
}

