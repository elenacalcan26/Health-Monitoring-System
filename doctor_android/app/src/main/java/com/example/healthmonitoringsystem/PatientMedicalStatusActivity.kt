package com.example.healthmonitoringsystem

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.healthmonitoringsystem.entities.Allergen
import com.example.healthmonitoringsystem.entities.Diagnose
import com.example.healthmonitoringsystem.entities.PatientMedicalStatus
import com.example.healthmonitoringsystem.entities.Treatment

class PatientMedicalStatusActivity: AppCompatActivity() {

    private lateinit var patientNameTextView: TextView
    private lateinit var allergiesContainer: LinearLayout
    private lateinit var diagnosisTextView: TextView
    private lateinit var treatmentTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_status)

        patientNameTextView = findViewById(R.id.patientNameTextView)
        allergiesContainer = findViewById(R.id.allergiesContainer)
        diagnosisTextView = findViewById(R.id.diagnosisTextView)
        treatmentTextView = findViewById(R.id.treatmentTextView)

        displayMedicalStatus()

    }

    private fun displayMedicalStatus() {
        val patientStatus = intent.getParcelableExtra<PatientMedicalStatus>("patientStatus")
        val patientFullName = intent.extras?.getString("patientFullName")

        patientNameTextView.text = "$patientFullName's Medical Status"

        patientStatus?.allergies?.let { displayAllergies(it) }
        patientStatus?.diagnose?.let { displayDiagnosis(it) }
        patientStatus?.treatment?.let { displayTreatment(it) }
    }

    private fun displayAllergies(allergies: List<Allergen>) {
        for (allergy in allergies) {
            val allergyTextView = TextView(this)
            allergyTextView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            allergyTextView.text = allergy.allergen
            allergiesContainer.addView(allergyTextView)
        }
    }

    private fun displayDiagnosis(diagnose: Diagnose) {
        val diagnoseText = "Diagnosis: ${diagnose.diagnosis}\n" +
                "Diagnosis Date: ${diagnose.diagnosis_date}"

        diagnosisTextView.text = diagnoseText
    }

    private fun displayTreatment(treatment: Treatment) {
        val treatmentText = "Treatment Name: ${treatment.treatment_name} \n" +
            "Medication Name: ${treatment.medication_name}\n" +
                "Start Date: ${treatment.start_date}\n" +
                "Dosage: ${treatment.dosage} \n" +
                "Frequency: ${treatment.frequency}"

        treatmentTextView.text = treatmentText
    }

}