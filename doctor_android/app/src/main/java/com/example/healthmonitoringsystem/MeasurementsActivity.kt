package com.example.healthmonitoringsystem

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthmonitoringsystem.adapters.MeasurementsAdapter
import com.example.healthmonitoringsystem.entities.MonitoredMeasurements

class MeasurementsActivity: AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MeasurementsAdapter
    private lateinit var monitoringStartDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("MeasurementsActivity", "Before setContentView")

        setContentView(R.layout.activity_patient_measurements)

        Log.d("MeasurementsActivity", "start MeasurementsActivity")

        monitoringStartDateTextView = findViewById(R.id.start_monitoring_date)
        recyclerView = findViewById(R.id.measurementsRecyclerView)
        adapter = MeasurementsAdapter(emptyList())

        var startDate = intent.extras?.getString("monitoringStartDate")
        monitoringStartDateTextView.text = "Monitoring since: $startDate"

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