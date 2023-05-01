package com.example.healthmonitoringsystem.models

data class DocProfileResp(
    val department_name: String,
    val full_name: String,
    val hospital_name: String,
    val name: String, // TODO change this name to department_name
    val phone: Long,
    val work_mail: String
)