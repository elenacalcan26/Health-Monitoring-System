package com.example.healthmonitoringsystem.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.healthmonitoringsystem.RetrofitClient
import com.example.healthmonitoringsystem.entities.MonitoredMeasurements
import com.example.healthmonitoringsystem.models.PatientMeasurementsResp
import com.example.healthmonitoringsystem.common.Result
import com.example.healthmonitoringsystem.extensions.toMonitoredMeasurementsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientMeasurementsRepository {
    fun getPatientMeasurements(patientId: Int): LiveData<Result<List<MonitoredMeasurements>>> {
        val result = MutableLiveData<Result<List<MonitoredMeasurements>>>()

        RetrofitClient.instance.getPatientMeasurements(patientId).enqueue(object: Callback<PatientMeasurementsResp> {
            override fun onResponse(call: Call<PatientMeasurementsResp>, response: Response<PatientMeasurementsResp>) {
                if (response.isSuccessful) {
                    val monitoredMeasurements = response.body()?.toMonitoredMeasurementsList() ?: emptyList()
                    Log.d("PatientMeasurementsOnResponse", response.body().toString())
                    result.value = Result.Success(monitoredMeasurements)
                }
            }

            override fun onFailure(call: Call<PatientMeasurementsResp>, t: Throwable) {
                result.value = Result.Error(Exception("Get Patient Measurements Failed!"))
                Log.d("PatientMeasurementsOnFailure", "Get Patient Measurements Failed!")
            }
        })

        return result
    }
}
