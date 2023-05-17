package com.example.healthmonitoringsystem

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthmonitoringsystem.adapters.MeasurementsAdapter
import com.example.healthmonitoringsystem.entities.MonitoredMeasurements

class MeasurementsActivity: AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MeasurementsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("MeasurementsActivity", "Before setContentView")

        setContentView(R.layout.activity_patient_measurements)

        Log.d("MeasurementsActivity", "start MeasurementsActivity")

        recyclerView = findViewById(R.id.measurementsRecyclerView)
        adapter = MeasurementsAdapter(emptyList())

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val measurementsList = intent.getParcelableArrayListExtra<MonitoredMeasurements>("measurementsList")

        Log.d("MeasurementsActivity", measurementsList.toString())

        measurementsList?.let {
            adapter.updateMeasurements(it)
        }

        recyclerView.adapter = adapter
    }
}