package com.example.healthmonitoringsystem.extensions

import com.example.healthmonitoringsystem.entities.DocProfile
import com.example.healthmonitoringsystem.models.DocProfileResp

fun DocProfileResp.toDocProfile(): DocProfile {
    return DocProfile(
        department_name = this.department_name,
        full_name = this.full_name,
        hospital_name = this.hospital_name,
        specialization = this.name,
        phone = this.phone,
        work_mail = this.work_mail
    )
}