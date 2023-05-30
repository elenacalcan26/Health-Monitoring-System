package com.example.healthmonitoringsystem

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthmonitoringsystem.adapters.OnPatientClickListener
import com.example.healthmonitoringsystem.adapters.PatientListAdapter
import com.example.healthmonitoringsystem.entities.Patient
import com.example.healthmonitoringsystem.viewmodel.PatientsViewModel
import java.util.ArrayList
import com.example.healthmonitoringsystem.common.Result

class PatientsListActivity: AppCompatActivity(), OnPatientClickListener {

    private lateinit var patientRecyclerView: RecyclerView
    private lateinit var adapter: PatientListAdapter
    private lateinit var patientsViewModel: PatientsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_patients)

        Log.d("PatientsListActivity", "must be in Patient List Activity")

        patientsViewModel = ViewModelProvider(this).get(PatientsViewModel::class.java)

        val patientList: ArrayList<Patient>? = intent.getParcelableArrayListExtra("patientList")

        patientRecyclerView = findViewById(R.id.patient_list_recyclerview)
        patientRecyclerView.layoutManager = LinearLayoutManager(this)

        adapter = PatientListAdapter(patientList ?: ArrayList())
        adapter.setOnPatientClickListener(this)

        patientRecyclerView.adapter = adapter
    }


    override fun onPatientClick(position: Int) {
        val clickedPatient = adapter.patientList[position]
        Log.d("PatientsListActivity", "item pressed $position & patientId = ${clickedPatient.patient_id}")

        patientsViewModel.getPatientDetails(clickedPatient.patient_id).observe(this)
        {
            result ->
                when (result) {
                    is Result.Success -> {
                        var patientDetails = result.data!!
                        Log.d("ClickedPatient", patientDetails.toString())
                        val intent: Intent = Intent(this, PatientProfileActivity::class.java ).apply {
                            putExtra("patientDetails", patientDetails)
                            putExtra("patientId", clickedPatient.patient_id)
                        }

                        startActivity(intent)
                    }

                    is Result.Error ->  Log.d("ClickedPatient", "Error getting patient profile")
                }
        }

    }
}