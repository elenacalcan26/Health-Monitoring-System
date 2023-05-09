package com.example.healthmonitoringsystem.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthmonitoringsystem.entities.Patient
import com.example.healthmonitoringsystem.repository.PatientRepository
import com.example.healthmonitoringsystem.common.Result
import com.example.healthmonitoringsystem.entities.PatientDetails

class PatientsViewModel : ViewModel() {

    private val patientRepository = PatientRepository()
    private val _patientList = MutableLiveData<Result<List<Patient>>>()
    val patientList: LiveData<Result<List<Patient>>>
        get() = _patientList

    fun fetchPatientList() {
        Log.d("PatientsViewModel", "fetchPatientList start")
        patientRepository.getListPatients().observeForever { result ->
            _patientList.value = result
        }
    }

    fun getPatientDetails(id: Int): LiveData<Result<PatientDetails>> {
        return patientRepository.getPatientDetails(id)
    }

}
