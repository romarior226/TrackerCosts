package com.example.trackercosts.data.network


data class ExpenseDto(
    val id: Int,
    val amount: Double,
    val category: String,
    val description: String = "",
    val date: Long,
    val currency: String
)