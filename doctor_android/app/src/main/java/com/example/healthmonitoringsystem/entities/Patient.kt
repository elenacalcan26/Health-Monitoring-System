package com.example.healthmonitoringsystem.entities

import android.os.Parcel
import android.os.Parcelable

data class Patient(
    val patient_id: Int,
    val full_name: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(patient_id)
        dest.writeString(full_name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Patient> {
        override fun createFromParcel(source: Parcel): Patient {
            return Patient(source)
        }

        override fun newArray(size: Int): Array<Patient?> {
            return arrayOfNulls(size)
        }
    }

}
