package com.example.healthmonitoringsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.healthmonitoringsystem.viewmodel.DocProfileViewModel
import com.example.healthmonitoringsystem.common.Result
import com.example.healthmonitoringsystem.viewmodel.PatientsViewModel



class MainMenuActivity : AppCompatActivity() {
    private lateinit var profileButton: Button
    private lateinit var patientsButton: Button
    private lateinit var docProfileViewModel: DocProfileViewModel
    private lateinit var patientsListViewModel: PatientsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        profileButton = findViewById(R.id.profile_button)
        patientsButton = findViewById(R.id.patients_button)

        docProfileViewModel = ViewModelProvider(this).get(DocProfileViewModel::class.java)
        patientsListViewModel = ViewModelProvider(this).get(PatientsViewModel::class.java)

        profileButton.setOnClickListener {
            viewProfilePressed()
        }

        patientsButton.setOnClickListener {
            Toast.makeText(this, "button pressed", Toast.LENGTH_SHORT).show()
            viewPatientsPressed()
        }
    }

    private fun viewProfilePressed() {
        docProfileViewModel.getDocProfile().observe(
            this
        ) { result ->
            when (result) {
                is Result.Success -> {
                    val intent: Intent = Intent(this, DoctorProfileActivity::class.java).apply {
                        putExtra("docProfile", result.data as Parcelable)
                    }

                    startActivity(intent)
                }

                is Result.Error -> {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun viewPatientsPressed() {
        Log.d("MainMenuActivity", "viewPatientsPressed() called")
        patientsListViewModel.fetchPatientList()
        patientsListViewModel.patientList.observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    Log.d("PatientsList", result.data.toString())
                    val intent = Intent(this, PatientsListActivity::class.java).apply {
                        putParcelableArrayListExtra("patientList", ArrayList(result.data))
                    }
                    Log.d("MainMenuActivity", "Before Starting ListPatientsActivity")
                    startActivity(intent)
                }
                is Result.Error -> {
                    Log.d("PatientsList", "Error fetching patient list: ${result.exception.message}")
                }

            }
        }
    }
}
