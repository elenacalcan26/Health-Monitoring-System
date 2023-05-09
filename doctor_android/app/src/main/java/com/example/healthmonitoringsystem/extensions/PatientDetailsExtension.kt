package com.example.healthmonitoringsystem.extensions

import com.example.healthmonitoringsystem.entities.PatientDetails
import com.example.healthmonitoringsystem.models.PatientDetailsResp


fun PatientDetailsResp.toPatientDetails(): PatientDetails {
    return PatientDetails(
        full_name = this.full_name,
        age = this.age,
        birth_date = this.birth_date,
        gender = this.gender,
        device_id = this.device_id,
        monitoring_start_date = this.monitoring_start_date
    )
}
