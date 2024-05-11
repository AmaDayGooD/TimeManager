package com.example.timemanager.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.timemanager.entity.Award
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


/**
 * Created by Alexander Shibaev on 09.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */
data class DataAward(
    override val idAward: Int? = null,
    val awardName: String = "",
    override val description: String = "",
    val price: String = "",
    val userAwards: UserAwards? = null,
) : Award {
    constructor(award: Award) : this(
        award.idAward,
        award.nameAward,
        award.description,
        "",
        null,
    )

    override val nameAward: String
        get() = awardName

    override val priceAward: Int
        get() = price.toIntOrNull() ?: 10

    override val awarded: UserAwards?
        get() = userAwards
}

data class UserAwards(
    val userId: Int,
    val awardId: Int,
    val awarded: String,
)