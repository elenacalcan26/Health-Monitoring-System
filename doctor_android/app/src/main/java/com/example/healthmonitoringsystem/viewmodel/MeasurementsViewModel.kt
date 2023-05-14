package com.example.healthmonitoringsystem.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthmonitoringsystem.entities.MonitoredMeasurements
import com.example.healthmonitoringsystem.repository.PatientMeasurementsRepository
import com.example.healthmonitoringsystem.common.Result

class MeasurementsViewModel: ViewModel() {
    private val patientMeasurementsRepository = PatientMeasurementsRepository()
    private val _measurementsList = MutableLiveData<Result<List<MonitoredMeasurements>>>()
    val measurementsList: LiveData<Result<List<MonitoredMeasurements>>>
        get() = _measurementsList

    fun fetchPatientMeasurements(patientId: Int) {
        Log.d("MeasurementsViewModel", "fetchPatientMeasurements start")
        patientMeasurementsRepository.getPatientMeasurements(patientId).observeForever { result ->
            _measurementsList.value = result
        }
    }
}
