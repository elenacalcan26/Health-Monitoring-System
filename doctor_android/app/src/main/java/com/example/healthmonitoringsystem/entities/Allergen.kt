package com.example.healthmonitoringsystem.entities

import android.os.Parcel
import android.os.Parcelable

data class Allergen(
    val allergen: String
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()!!)

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(allergen)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR: Parcelable.Creator<Allergen> {
        override fun createFromParcel(source: Parcel): Allergen = Allergen(source)

        override fun newArray(size: Int): Array<Allergen?> = arrayOfNulls(size)
    }
}
