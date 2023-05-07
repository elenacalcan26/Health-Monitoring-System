package com.example.healthmonitoringsystem

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthmonitoringsystem.adapters.PatientListAdapter
import com.example.healthmonitoringsystem.entities.Patient
import java.util.ArrayList

class PatientsListActivity: AppCompatActivity() {

    private lateinit var patientRecyclerView: RecyclerView
    private lateinit var adapter: PatientListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_patients)

        Log.d("PatientsListActivity", "must be in Patient List Activity")

        val patientList: ArrayList<Patient>? = intent.getParcelableArrayListExtra("patientList")

        patientRecyclerView = findViewById(R.id.patient_list_recyclerview)
        patientRecyclerView.layoutManager = LinearLayoutManager(this)

        adapter = PatientListAdapter(patientList ?: ArrayList())
        patientRecyclerView.adapter = adapter
    }
}