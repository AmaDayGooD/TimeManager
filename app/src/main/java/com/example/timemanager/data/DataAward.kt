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
    val awarded: String? = null,
) : Award {
    constructor(award: Award) : this(
        award.idAward,
        award.nameAward,
        award.description,
        "",
        ""
    )

    override val nameAward: String
        get() = awardName

    override val priceAward: Int
        get() = price.toIntOrNull() ?: 10

    override val awardedDateTime: LocalDateTime?
        @RequiresApi(Build.VERSION_CODES.O)
        get() = awarded.let { LocalDateTime.ofInstant(Instant.parse(it), ZoneId.of("UTC")) }
}