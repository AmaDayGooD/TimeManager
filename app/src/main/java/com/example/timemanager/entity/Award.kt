package com.example.timemanager.entity

import java.time.LocalDateTime


/**
 * Created by Alexander Shibaev on 09.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */
interface Award {
    val idAward: Int?
    val nameAward: String
    val description: String
    val priceAward: Int
    val awardedDateTime: LocalDateTime?
}