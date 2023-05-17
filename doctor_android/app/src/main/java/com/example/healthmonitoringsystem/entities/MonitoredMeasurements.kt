package com.example.healthmonitoringsystem.entities

import android.os.Parcel
import android.os.Parcelable


data class MonitoredMeasurements(
    val timestamp: String,
    val values: List<Measurement>
) : Parcelable {

    constructor(parcel: Parcel): this(
        parcel.readString()!!,
        parcel.createTypedArrayList(Measurement.CREATOR)!!
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(timestamp)
        dest.writeTypedList(values)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MonitoredMeasurements> {
        override fun createFromParcel(source: Parcel): MonitoredMeasurements {
            return MonitoredMeasurements(parcel = source)
        }

        override fun newArray(size: Int): Array<MonitoredMeasurements?> {
            return arrayOfNulls(size)
        }
    }
}
