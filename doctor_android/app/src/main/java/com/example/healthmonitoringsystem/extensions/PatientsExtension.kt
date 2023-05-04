package com.example.healthmonitoringsystem.extensions

import com.example.healthmonitoringsystem.entities.Patient
import com.example.healthmonitoringsystem.models.PatientResp

fun PatientResp.toPatient(): Patient {
    return Patient(
        patient_id = this.patient_id,
        full_name = this.full_name
    )
}

