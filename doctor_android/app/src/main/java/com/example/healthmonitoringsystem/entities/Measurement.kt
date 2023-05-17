package com.example.healthmonitoringsystem.entities

import android.os.Parcel
import android.os.Parcelable

data class Measurement(
    val measurementType: String,
    val value: Int
) : Parcelable {

    constructor(parcel: Parcel): this(
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(measurementType)
        dest.writeInt(value)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Measurement> {
        override fun createFromParcel(source: Parcel) = Measurement(source)

        override fun newArray(size: Int): Array<Measurement?> = arrayOfNulls(size)
    }
}
