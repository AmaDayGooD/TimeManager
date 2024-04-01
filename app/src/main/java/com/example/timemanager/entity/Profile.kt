package com.example.timemanager.entity

import com.example.timemanager.data.local_data_base.Role

interface Profile {
    val login: String?
    val password: String?
    val username: String?
    val userRole: Role?
    val count: Int?
}