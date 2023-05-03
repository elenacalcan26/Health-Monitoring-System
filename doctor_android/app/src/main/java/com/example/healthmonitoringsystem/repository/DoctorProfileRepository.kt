package com.example.healthmonitoringsystem.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.healthmonitoringsystem.RetrofitClient
import com.example.healthmonitoringsystem.models.DocProfileResp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.healthmonitoringsystem.common.Result
import com.example.healthmonitoringsystem.entities.DocProfile
import com.example.healthmonitoringsystem.extensions.toDocProfile

class DoctorProfileRepository {
    fun getDocProfile(): LiveData<Result<DocProfile>> {
        val result = MutableLiveData<Result<DocProfile>>()
        RetrofitClient.instance.getDocProfile().enqueue(
            object: Callback<DocProfileResp> {
                override fun onResponse(call: Call<DocProfileResp>, response: Response<DocProfileResp>) {
                    if (response.isSuccessful) {
                        val docProfile = response.body()?.toDocProfile()
                        Log.d("DocProfileRepository", docProfile.toString())
                        if (docProfile != null) {
                            result.value = Result.Success(docProfile!!)
                        } else {
                            result.value = Result.Error(Exception("Get profile failed"))
                        }

                    } else {
                        result.value = Result.Error(Exception("Get profile failed"))
                    }
                }

                override fun onFailure(call: Call<DocProfileResp>, t: Throwable) {
                    result.value = Result.Error(Exception("Get profile failed"))
                    Log.d("DoctorRepositoryImpl", t.message.toString())
                }
            }
        )

        return result
    }
}