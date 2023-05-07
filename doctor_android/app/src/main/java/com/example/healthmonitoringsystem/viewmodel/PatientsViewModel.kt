package com.example.healthmonitoringsystem.viewmodel

import android.util.Log
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
        Log.d("PatientsViewModel", "fetchPatientList start")
//        viewModelScope.launch {
//            try {
//                Log.d("PatientsViewModel", "fetchPatientList coroutine started")
//                val result = patientRepository.getListPatients()
//                _patientList.postValue(result.value)
//            } catch (e: Exception) {
//                Log.e("PatientsViewModel", "Error fetching patient list", e)
//                _patientList.postValue(Result.Error(e))
//            }
//        }
        patientRepository.getListPatients().observeForever { result ->
            _patientList.value = result
        }
    }

}
