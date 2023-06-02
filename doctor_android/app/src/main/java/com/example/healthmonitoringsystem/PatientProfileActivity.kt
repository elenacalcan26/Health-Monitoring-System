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
import com.example.healthmonitoringsystem.viewmodel.MedicalStatusViewModel

class PatientProfileActivity: AppCompatActivity() {

    private lateinit var measurementsButton: Button
    private lateinit var patientMedicalStatusButton: Button
    private lateinit var measurementsChartButton: Button
    private lateinit var measurementsViewModel: MeasurementsViewModel
    private lateinit var medicalStatusViewModel: MedicalStatusViewModel
    private lateinit var startMonitoringDate: String
    private lateinit var patientFullName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_profile)

        val patientId = intent.getIntExtra("patientId", -1)
        val details = intent.extras?.getParcelable<PatientDetails>("patientDetails")
        displayPatientInfo(details!!)
        startMonitoringDate = formatDate(details.monitoring_start_date)
        patientFullName = details.full_name

        measurementsButton = findViewById(R.id.patient_measurements_button)
        patientMedicalStatusButton = findViewById(R.id.patient_status_button)
        measurementsChartButton = findViewById(R.id.measurements_chart_button)

        measurementsViewModel = ViewModelProvider(this).get(MeasurementsViewModel::class.java)
        medicalStatusViewModel = ViewModelProvider(this).get(MedicalStatusViewModel::class.java)

        measurementsButton.setOnClickListener {
            getPatientMeasurements(patientId)
        }

        patientMedicalStatusButton.setOnClickListener {
            viewPatientMedicalStatus(patientId)
        }

        measurementsChartButton.setOnClickListener {
            viewPatientMeasurementsChart(patientId)
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

    fun viewPatientMedicalStatus(patientId: Int) {
        Log.d("PatientProfileActivity", "Medical Status button pressed")

        medicalStatusViewModel.getMedicalStatus(patientId).observe(this) { result ->

            when (result) {
                is Result.Success -> {
                    Log.d("PatientMedicalStatus", result.data.toString())
                    val intent: Intent = Intent(this, PatientMedicalStatusActivity::class.java).apply {
                        putExtra("patientStatus", result.data)
                        putExtra("patientFullName", patientFullName)
                    }
                    startActivity(intent)
                }

                is Result.Error ->
                    Log.d("PatientMedicalStatus", "Error getting status ${result.exception}")
            }
        }
    }


    fun getPatientMeasurements(patientId: Int) {
        Log.d("PatientProfileActivity", "Measurements button pressed")
        Toast.makeText(this, "button pressed", Toast.LENGTH_SHORT).show()

        measurementsViewModel.fetchPatientMeasurements(patientId)
        measurementsViewModel.measurementsList.observe(this) {result ->
            when (result) {
                is Result.Success -> {
                    Log.d("PatientProfileActivity", result.data.toString())

                    val intent = result.data?.takeIf { it.isNotEmpty() }?.let { data ->
                        Intent(this, MeasurementsActivity::class.java).apply {
                            putParcelableArrayListExtra("measurementsList", ArrayList(data))
                            putExtra("monitoringStartDate", startMonitoringDate)
                        }
                    } ?: Intent(this, NoDataMeasuredMessageActivity::class.java)

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

    private fun viewPatientMeasurementsChart(patientId: Int) {
        measurementsViewModel.fetchPatientMeasurements(patientId)
        measurementsViewModel.measurementsList.observe(this) { result ->
            when (result) {
                is Result.Success ->
                {
                    val intent = result.data?.takeIf { it.isNotEmpty() }?.let { data ->
                        Intent(this, MeasurementsChartActivity::class.java).apply {
                            putParcelableArrayListExtra("measurementsList", ArrayList(data))
                        }
                    } ?: Intent(this, NoDataMeasuredMessageActivity::class.java)
                    startActivity(intent)
                }

                is Result.Error ->
                    Log.d(
                        "PatientMeasurements",
                        "Error fetching patient measurements: ${result.exception.message}")
            }
        }
    }

    private fun formatDate(monitoringStartDate: String): String {
        val startIndex = monitoringStartDate.indexOf(",") + 2
        val endIndex = monitoringStartDate.lastIndexOf(":")
        return monitoringStartDate.substring(startIndex, endIndex)
    }

}
