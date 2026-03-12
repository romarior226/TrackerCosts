package com.example.trackercosts.data

import com.example.trackercosts.data.entity.ExpenseDbModel
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