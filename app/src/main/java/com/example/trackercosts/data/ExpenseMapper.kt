package com.example.trackercosts.data

import android.util.Log
import com.example.trackercosts.data.entity.ExpenseDbModel
import com.example.trackercosts.data.network.ExpenseDto
import com.example.trackercosts.domain.entity.Category
import com.example.trackercosts.domain.entity.Expense

fun ExpenseDbModel.toExpense(): Expense {
    return Expense(
        id = this.id,
        amount = this.amount,
        category = this.category,
        description = this.description,
        date = this.date,
        currency = this.currency
    )
}

fun ExpenseDto.toExpense(): Expense {
    val randomDate = System.currentTimeMillis() - (0..30).random().toLong() * 24 * 60 * 60 * 1000L
    Log.d("Mapper", "date: $randomDate")
    return Expense(
        id = 0,
        amount = this.amount,
        category = Category.ALL,
        description = this.description,
        date = randomDate,
        currency = this.currency
    )
}

fun Expense.toDbModel(): ExpenseDbModel {
    return ExpenseDbModel(
        id = this.id,
        amount = this.amount,
        category = this.category,
        description = this.description,
        date = this.date,
        currency = this.currency
    )
}