package com.example.healthmonitoringsystem.entities

import android.os.Parcel
import android.os.Parcelable

data class PatientDetails(
    val full_name: String,
    val age: Int,
    val birth_date: String,
    val gender: String,
    val device_id: String,
    val monitoring_start_date: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
        )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(full_name)
        dest.writeInt(age)
        dest.writeString(birth_date)
        dest.writeString(gender)
        dest.writeString(device_id)
        dest.writeString(monitoring_start_date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PatientDetails> {
        override fun createFromParcel(source: Parcel): PatientDetails {
            return PatientDetails(source)
        }

        override fun newArray(size: Int): Array<PatientDetails?> {
            return arrayOfNulls(size)
        }
    }
}
