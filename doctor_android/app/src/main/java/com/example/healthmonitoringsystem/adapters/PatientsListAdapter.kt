package com.example.healthmonitoringsystem.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthmonitoringsystem.R
import com.example.healthmonitoringsystem.entities.Patient

class PatientListAdapter(private val patientList: List<Patient>) : RecyclerView.Adapter<PatientListAdapter.PatientViewHolder>() {

    inner class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val patientId: TextView = itemView.findViewById(R.id.patient_id)
        val patientName: TextView = itemView.findViewById(R.id.patient_name)

        fun bind(patient: Patient) {
            patientId.text = patient.patient_id.toString()
            patientName.text = patient.full_name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_design, parent, false)
        return PatientViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val currentPatient = patientList[position]
        holder.bind(currentPatient)
    }

    override fun getItemCount() = patientList.size
}
