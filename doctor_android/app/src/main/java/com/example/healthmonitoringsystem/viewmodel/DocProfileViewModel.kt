package com.example.healthmonitoringsystem.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.healthmonitoringsystem.entities.DocProfile
import com.example.healthmonitoringsystem.repository.DoctorProfileRepository
import com.example.healthmonitoringsystem.common.Result

class DocProfileViewModel: ViewModel() {
    private val docProfileRepository = DoctorProfileRepository()

    fun getDocProfile(): LiveData<Result<DocProfile>> {
        return docProfileRepository.getDocProfile()
    }
}