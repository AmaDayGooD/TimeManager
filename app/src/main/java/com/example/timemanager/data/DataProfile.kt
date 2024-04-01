package com.example.timemanager.data

import com.example.timemanager.data.local_data_base.Role
import com.example.timemanager.entity.Profile

data class DataProfile(
    override val login: String? = null,
    override val password: String? = null,
    override val username: String? = null,
    val role: String? = null,
    val balance: String? = null
) : Profile {
    constructor(profile: Profile) : this(
        profile.login,
        profile.password,
        profile.username,
        "",
        ""
    )

    override var userRole: Role
        get() = Role.valueOf(role ?: Role.Child.toString())
        set(value) {}

    override val count: Int?
        get() = balance?.toIntOrNull()
}