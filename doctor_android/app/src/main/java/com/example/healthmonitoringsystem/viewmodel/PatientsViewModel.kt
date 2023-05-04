package com.example.healthmonitoringsystem.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthmonitoringsystem.entities.Patient
import com.example.healthmonitoringsystem.repository.PatientRepository
import com.example.healthmonitoringsystem.common.Result
import kotlinx.coroutines.launch

class PatientsViewModel : ViewModel() {

    private val patientRepository = PatientRepository()
    private val _patientList = MutableLiveData<Result<List<Patient>>>()
    val patientList: LiveData<Result<List<Patient>>>
        get() = _patientList

    fun fetchPatientList() {
        viewModelScope.launch {
            val result = patientRepository.getListPatients()
            _patientList.setValue(result.value)
        }
    }
}
