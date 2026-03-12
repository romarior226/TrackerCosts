package com.example.trackercosts.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trackercosts.domain.entity.Category

@Entity(tableName = "expense")
data class ExpenseDbModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val amount: Double,
    val category: Category,
    val description: String = "",
    val date: Long,
    val currency: String
)