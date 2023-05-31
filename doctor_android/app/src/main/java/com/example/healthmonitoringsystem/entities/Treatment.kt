package com.example.healthmonitoringsystem.entities

import android.os.Parcel
import android.os.Parcelable

data class Treatment(
    val dosage: String,
    val frequency: String,
    val medication_name: String,
    val start_date: String,
    val treatment_name: String
) : Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(dosage)
        dest.writeString(frequency)
        dest.writeString(medication_name)
        dest.writeString(start_date)
        dest.writeString(treatment_name)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR: Parcelable.Creator<Treatment> {
        override fun createFromParcel(source: Parcel): Treatment = Treatment(source)

        override fun newArray(size: Int): Array<Treatment?> = arrayOfNulls(size)
    }
}
