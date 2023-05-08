package com.example.healthmonitoringsystem.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthmonitoringsystem.R
import com.example.healthmonitoringsystem.entities.Patient

class PatientListAdapter(private val patientList: List<Patient>) : RecyclerView.Adapter<PatientListAdapter.PatientViewHolder>() {

    private lateinit var onPatientClickListener: OnPatientClickListener

    inner class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val patientId: TextView = itemView.findViewById(R.id.patient_id)
        val patientName: TextView = itemView.findViewById(R.id.patient_name)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onPatientClickListener?.onPatientClick(adapterPosition)
        }

        fun bind(patient: Patient) {
            patientId.text = patient.patient_id.toString()
            patientName.text = patient.full_name

            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onPatientClickListener?.onPatientClick(position)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        val holder = PatientViewHolder(itemView)
        holder.itemView.setOnClickListener(holder)
        return holder
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val currentPatient = patientList[position]
        holder.bind(currentPatient)

        holder.itemView.setOnClickListener {
            onPatientClickListener?.onPatientClick(position)
        }
    }

    override fun getItemCount() = patientList.size

    fun setOnPatientClickListener(listener: OnPatientClickListener) {
        this.onPatientClickListener = listener
    }
}
