package com.example.healthmonitoringsystem

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.healthmonitoringsystem.entities.DocProfile

class DoctorProfileActivity: AppCompatActivity() {

    lateinit var docFullNameTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_profile)

        var bundle = intent.extras
        val docProfile = bundle?.getParcelable<DocProfile>("docProfile")

        docFullNameTextView = findViewById(R.id.user_name_text)
        docFullNameTextView.text = "Name: ${docProfile?.full_name}"
    }
}
