package com.example.healthmonitoringsystem.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.healthmonitoringsystem.RetrofitClient
import com.example.healthmonitoringsystem.common.Result
import com.example.healthmonitoringsystem.entities.Patient
import com.example.healthmonitoringsystem.entities.PatientDetails
import com.example.healthmonitoringsystem.extensions.toPatient
import com.example.healthmonitoringsystem.extensions.toPatientDetails
import com.example.healthmonitoringsystem.models.PatientDetailsResp
import com.example.healthmonitoringsystem.models.PatientResp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientRepository {
    fun getListPatients(): LiveData<Result<List<Patient>>> {
        val result = MutableLiveData<Result<List<Patient>>>()

        RetrofitClient.instance.getPatients().enqueue(object : Callback<List<PatientResp>> {
            override fun onResponse(call: Call<List<PatientResp>>, response: Response<List<PatientResp>>) {
                if (response.isSuccessful) {
                    val patients = response.body()?.map {
                        patientResp -> patientResp.toPatient()
                    }
                    Log.d("PatientRepositoryListPatients", patients.toString())
                    result.value = Result.Success(patients ?: emptyList())
                } else {
                    result.value = Result.Error(Exception("Get patients list failed"))
                    Log.d("PatientRepositoryListPatients", "Response unsuccessful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<PatientResp>>, t: Throwable) {
                result.value = Result.Error(Exception("Get patients list failed"))
                Log.d("PatientRepositoryListPatients", t.message.toString())
            }
        })
        return result
    }

    fun getPatientDetails(patientId: Int): LiveData<Result<PatientDetails>> {
        val result = MutableLiveData<Result<PatientDetails>>()

        RetrofitClient.instance.getPatientDetails(patientId).enqueue(object : Callback<PatientDetailsResp> {
            override fun onResponse(call: Call<PatientDetailsResp>, response: Response<PatientDetailsResp>) {
                if (response.isSuccessful) {
                    val patient = response.body()?.toPatientDetails()
                    Log.d("PatientRepository", patient.toString())
                    result.value = Result.Success(patient)
                }
            }

            override fun onFailure(call: Call<PatientDetailsResp>, t: Throwable) {
                result.value = Result.Error(Exception("Get patient details failed"))
                Log.d("PatientRepository", t.message.toString())
            }
        })

        return result
    }

}