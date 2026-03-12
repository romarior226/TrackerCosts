package com.example.trackercosts.domain.entity

import java.time.LocalDateTime

data class Expense(
    val id: Int ,
    val amount: Double,
    val category: Category,
    val description: String = "",
    val date: Long,
    val currency: String
)
