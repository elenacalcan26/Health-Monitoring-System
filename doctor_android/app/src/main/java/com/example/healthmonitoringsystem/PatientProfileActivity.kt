package com.example.healthmonitoringsystem

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.healthmonitoringsystem.entities.PatientDetails
import com.example.healthmonitoringsystem.viewmodel.PatientsViewModel
import com.example.healthmonitoringsystem.common.Result

class PatientProfileActivity: AppCompatActivity() {

    private lateinit var viewModel: PatientsViewModel
    private lateinit var patientDetails: PatientDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_profile)

        val patientId = intent.getIntExtra("patientId", -1)

        viewModel = ViewModelProvider(this).get(PatientsViewModel::class.java)
        viewModel.getPatientDetails(patientId)

        viewModel.getPatientDetails(patientId).observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    patientDetails = result.data!!
                    Log.d("PatientProfile", patientDetails.toString())
                }

                is Result.Error -> {
                    Toast.makeText(this, "Error getting patient details", Toast.LENGTH_SHORT).show()
                    Log.d("PatientProfile", "Error getting patient profile")
                }
            }
        }
    }
}
