package com.example.healthmonitoringsystem.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.healthmonitoringsystem.common.Result
import com.example.healthmonitoringsystem.RetrofitClient
import com.example.healthmonitoringsystem.entities.PatientMedicalStatus
import com.example.healthmonitoringsystem.models.PatientMedicalStatusResp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientMedicalStatusRepository {
    fun getPatientMedicalStatus(patientId: Int): LiveData<Result<PatientMedicalStatus>> {
        val result = MutableLiveData<Result<PatientMedicalStatus>>()

        RetrofitClient.instance.getPatientMedicalStatus(patientId).enqueue(object: Callback<PatientMedicalStatusResp> {
            override fun onResponse(call: Call<PatientMedicalStatusResp>, response: Response<PatientMedicalStatusResp>) {
                if (response.isSuccessful) {
                    Log.d("PatientMedicalStatusOnResponse", response.body().toString())
                    val medicalStatus = response.body()?.status
                    result.value = Result.Success(medicalStatus)

                } else {
                    Log.d("PatientMedicalStatusOnResponse", "on Response is not successful")
                }
            }

            override fun onFailure(call: Call<PatientMedicalStatusResp>, t: Throwable) {
                result.value = Result.Error(Exception("Get Patient Medical Status failed!"))
                Log.d("PatientMedicalStatusOnFailure", t.message.toString())
            }
        })

        return result
    }
}