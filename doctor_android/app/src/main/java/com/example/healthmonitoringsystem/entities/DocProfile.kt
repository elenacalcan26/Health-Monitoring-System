package com.example.healthmonitoringsystem.entities

import android.os.Parcel
import android.os.Parcelable

data class DocProfile(
    val department_name: String,
    val full_name: String,
    val hospital_name: String,
    val specialization: String,
    val phone: Long,
    val work_mail: String
) : Parcelable {
    constructor(parcel : Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readString()!!
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(department_name)
        dest.writeString(full_name)
        dest.writeString(hospital_name)
        dest.writeString(specialization)
        dest.writeLong(phone)
        dest.writeString(work_mail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DocProfile> {
        override fun createFromParcel(source: Parcel): DocProfile {
            return DocProfile(source)
        }

        override fun newArray(size: Int): Array<DocProfile?> {
            return arrayOfNulls(size)
        }
    }
}
