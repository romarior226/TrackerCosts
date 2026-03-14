package com.example.trackercosts.domain.repo

import com.example.trackercosts.domain.entity.Expense

interface ExpenseRepository {
    suspend fun getExpensesDetails(
        category: String,
        sortType: Byte,
        dateFrom: Long,
        dateTo: Long
    ): List<Expense>
    suspend fun getAllExpense(): List<Expense>

    suspend fun getExpenses(category: String, sortType: Byte): List<Expense>

    suspend fun addExpense(expense: Expense): Long

    suspend fun deleteExpense(expense: Expense)

    suspend fun updateExpense(expense: Expense)


}