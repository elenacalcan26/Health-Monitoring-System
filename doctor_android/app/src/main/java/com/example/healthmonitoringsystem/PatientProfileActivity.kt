package com.example.healthmonitoringsystem

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.healthmonitoringsystem.entities.PatientDetails
import com.example.healthmonitoringsystem.common.Result
import com.example.healthmonitoringsystem.viewmodel.MeasurementsViewModel

class PatientProfileActivity: AppCompatActivity() {

    private lateinit var measurementsButton: Button
    private lateinit var measurementsViewModel: MeasurementsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_profile)

        val patientId = intent.getIntExtra("patientId", -1)
        val details = intent.extras?.getParcelable<PatientDetails>("patientDetails")
        displayPatientInfo(details!!)

        measurementsButton = findViewById(R.id.patient_measurements_button)

        measurementsViewModel = ViewModelProvider(this).get(MeasurementsViewModel::class.java)


        measurementsButton.setOnClickListener {
            getPatientMeasurements(patientId)
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

    fun getPatientMeasurements(patientId: Int) {
        Log.d("PatientProfileActivity", "Measurements button pressed")
        Toast.makeText(this, "button pressed", Toast.LENGTH_SHORT).show()

        measurementsViewModel.fetchPatientMeasurements(patientId)
        measurementsViewModel.measurementsList.observe(this) {result ->
            when (result) {
                is Result.Success -> {
                    Log.d("PatientProfileActivity", result.data.toString())
                    val intent = Intent(this, MeasurementsActivity::class.java).apply {
                        putParcelableArrayListExtra("measurementsList", ArrayList(result.data))
                    }
                    startActivity(intent)
                }

                is Result.Error -> {
                    Log.d(
                        "PatientMeasurements",
                        "Error fetching patient measurements: ${result.exception.message}")
                }
            }
        }
    }
}
