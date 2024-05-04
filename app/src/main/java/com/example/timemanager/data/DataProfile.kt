package com.example.timemanager.data

import com.example.timemanager.data.local_data_base.Role
import com.example.timemanager.entity.Profile

data class DataProfile(
    override val id: Int = 0,
    override val login: String? = null,
    override val password: String? = null,
    override val username: String? = null,
    val role: String? = null,
    val balance: String? = "0",
) : Profile {
    constructor(profile: Profile) : this(
        profile.id,
        profile.login,
        profile.password,
        profile.username,
        "",
        "0"
    )

    override var userRole: Role
        get() = Role.valueOf(role ?: Role.Child.toString())
        set(value) {}

    override val count: Int
        get() = balance?.toInt() ?: 0
}