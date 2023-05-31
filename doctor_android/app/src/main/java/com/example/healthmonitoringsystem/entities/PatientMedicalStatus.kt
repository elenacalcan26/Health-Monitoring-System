package com.example.healthmonitoringsystem.entities

import android.os.Parcel
import android.os.Parcelable

data class PatientMedicalStatus(
    val allergies: List<Allergen>,
    val diagnose: Diagnose,
    val treatment: Treatment
): Parcelable {
    constructor(parcel: Parcel): this(
        parcel.createTypedArrayList(Allergen.CREATOR)!!,
        parcel.readParcelable(Diagnose::class.java.classLoader)!!,
        parcel.readParcelable(Treatment::class.java.classLoader)!!
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeTypedList(allergies)
        dest.writeParcelable(diagnose, flags)
        dest.writeParcelable(treatment, flags)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR: Parcelable.Creator<PatientMedicalStatus> {
        override fun createFromParcel(source: Parcel): PatientMedicalStatus = PatientMedicalStatus(source)

        override fun newArray(size: Int): Array<PatientMedicalStatus?> = arrayOfNulls(size)
    }
}
