package com.example.healthmonitoringsystem

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.healthmonitoringsystem.entities.DocProfile

class DoctorProfileActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_profile)

        var bundle = intent.extras
        val docProfile = bundle?.getParcelable<DocProfile>("docProfile")

        docProfile?.let { displayDoctorInfo(it) }
    }

    fun displayDoctorInfo(docProfile: DocProfile) {
        var docFullNameTextView: TextView = findViewById(R.id.user_name_text)
        var docMail: TextView = findViewById(R.id.doc_mail)
        var docPhone: TextView = findViewById(R.id.doc_phone)
        var docSpecialization: TextView = findViewById(R.id.doc_specialization)
        var docHospital: TextView = findViewById(R.id.doc_hospital)
        docFullNameTextView.text = "Name: ${docProfile?.full_name}"
        docMail.text = "Mail: ${docProfile.work_mail}"
        docPhone.text = "Phone: ${docProfile.phone}"
        docSpecialization.text = "Specialization: ${docProfile.specialization}"
        docHospital.text = "Hospital: ${docProfile.hospital_name}"
    }
}
