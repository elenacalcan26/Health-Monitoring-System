package com.example.healthmonitoringsystem.entities

data class PatientMedicalStatus(
    val allergies: List<Allergen>,
    val diagnose: Diagnose,
    val treatment: Treatment
)