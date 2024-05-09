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
    override val idAward: Int?,
    override val nameAward: String,
    override val description: String,
    val price: String,
    val awarded: String?,
) : Award {
    constructor(award: Award) : this(
        award.idAward,
        award.nameAward,
        award.description,
        "",
        null
    )

    override val priceAward: Int
        get() = price.toIntOrNull() ?: 10

    override val awardedDateTime: LocalDateTime
        @RequiresApi(Build.VERSION_CODES.O)
        get() = LocalDateTime.ofInstant(Instant.parse(awarded), ZoneId.of("UTC"))
}