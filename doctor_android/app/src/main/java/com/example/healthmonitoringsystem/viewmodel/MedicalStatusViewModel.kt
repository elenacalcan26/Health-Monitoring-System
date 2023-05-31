package com.example.healthmonitoringsystem.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.healthmonitoringsystem.common.Result
import com.example.healthmonitoringsystem.entities.PatientMedicalStatus
import com.example.healthmonitoringsystem.repository.PatientMedicalStatusRepository

class MedicalStatusViewModel: ViewModel() {
    private val medicalStatusRepository = PatientMedicalStatusRepository()

    fun getMedicalStatus(id: Int): LiveData<Result<PatientMedicalStatus>> {
        return medicalStatusRepository.getPatientMedicalStatus(id)
    }
}
