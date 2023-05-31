package com.example.healthmonitoringsystem

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.healthmonitoringsystem.entities.PatientMedicalStatus

class PatientMedicalStatusActivity: AppCompatActivity() {

    private lateinit var allergiesContainer: LinearLayout
    private lateinit var diagnosisTextView: TextView
    private lateinit var treatmentTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_status)

        allergiesContainer = findViewById(R.id.allergiesContainer)
        diagnosisTextView = findViewById(R.id.diagnosisTextView)
        treatmentTextView = findViewById(R.id.treatmentTextView)

        val patientStatus = intent.getParcelableExtra<PatientMedicalStatus>("patientStatus")
        Log.d("PatientMedicalStatusActivity", patientStatus.toString())

    }
}