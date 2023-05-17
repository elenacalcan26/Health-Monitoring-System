package com.example.healthmonitoringsystem.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.healthmonitoringsystem.R
import com.example.healthmonitoringsystem.entities.MonitoredMeasurements

class MeasurementsAdapter(var measurementsList: List<MonitoredMeasurements>):
    RecyclerView.Adapter<MeasurementsAdapter.MeasurementsViewHolder>() {

        inner class MeasurementsViewHolder(itemView: View) : ViewHolder(itemView) {
            val timestampTextView: TextView = itemView.findViewById(R.id.timestampTextView)
            val bpmTextView: TextView = itemView.findViewById(R.id.bpmTextView)
            val spo2TextView: TextView = itemView.findViewById(R.id.spo2TextView)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasurementsViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.measurement_item, parent, false)
        return MeasurementsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return measurementsList.size
    }

    override fun onBindViewHolder(holder: MeasurementsViewHolder, position: Int) {
        val measurement = measurementsList[position]
        holder.timestampTextView.text = measurement.timestamp
        val bpmValue = measurement.values.find { it.measurementType == "BPM" }?.value.toString()
        val spo2Value = measurement.values.find { it.measurementType == "SPO2" }?.value.toString()
        holder.bpmTextView.text = bpmValue
        holder.spo2TextView.text = spo2Value
    }

    fun updateMeasurements(measurements: List<MonitoredMeasurements>) {
        measurementsList = measurements
        notifyDataSetChanged()
    }

}
