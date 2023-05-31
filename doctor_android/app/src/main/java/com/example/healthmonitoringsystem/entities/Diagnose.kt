package com.example.healthmonitoringsystem.entities

import android.os.Parcel
import android.os.Parcelable

data class Diagnose(
    val diagnosis: String,
    val diagnosis_date: String
) : Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(diagnosis)
        dest.writeString(diagnosis_date)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR: Parcelable.Creator<Diagnose> {
        override fun createFromParcel(source: Parcel): Diagnose = Diagnose(source)

        override fun newArray(size: Int): Array<Diagnose?> = arrayOfNulls(size)
    }
}
