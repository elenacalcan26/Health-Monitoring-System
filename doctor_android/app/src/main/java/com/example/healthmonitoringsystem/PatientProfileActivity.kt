package com.example.healthmonitoringsystem

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.healthmonitoringsystem.entities.PatientDetails
import com.example.healthmonitoringsystem.viewmodel.PatientsViewModel
import com.example.healthmonitoringsystem.common.Result

class PatientProfileActivity: AppCompatActivity() {

    private lateinit var viewModel: PatientsViewModel
    private lateinit var patientDetails: PatientDetails
    private lateinit var measurementsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_profile)

        val patientId = intent.getIntExtra("patientId", -1)

        measurementsButton = findViewById(R.id.patient_measurements_button)

        viewModel = ViewModelProvider(this).get(PatientsViewModel::class.java)
        viewModel.getPatientDetails(patientId)

        viewModel.getPatientDetails(patientId).observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    patientDetails = result.data!!
                    Log.d("PatientProfile", patientDetails.toString())
                    displayPatientInfo(patientDetails)
                }

                is Result.Error -> {
                    Toast.makeText(this, "Error getting patient details", Toast.LENGTH_SHORT).show()
                    Log.d("PatientProfile", "Error getting patient profile")
                }
            }
        }

        measurementsButton.setOnClickListener {
            getPatientMeasurements()
        }
    }

    fun displayPatientInfo(patientDetails: PatientDetails) {
        val patientFullName: TextView = findViewById(R.id.patient_full_name)
        val patientAge: TextView = findViewById(R.id.patient_age)
        val patientGender: TextView = findViewById(R.id.patient_gender)
        val patientAssignedDevice: TextView = findViewById(R.id.patient_device)
        patientFullName.text = "Name: ${patientDetails.full_name}"
        patientAge.text = "Age: ${patientDetails.age}"
        patientGender.text = "Gender: ${patientDetails.gender}"
        patientAssignedDevice.text = "Device: ${patientDetails.device_id}"
    }

    fun getPatientMeasurements() {
        Log.d("PatientProfileActivity", "Measurements button pressed")
        Toast.makeText(this, "button pressed", Toast.LENGTH_SHORT).show()
    }

}
