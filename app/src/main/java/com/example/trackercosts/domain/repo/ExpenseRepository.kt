package com.example.trackercosts.domain.repo

import com.example.trackercosts.data.entity.ExpenseDbModel
import com.example.trackercosts.domain.entity.Category
import com.example.trackercosts.domain.entity.Expense

interface   ExpenseRepository {
    suspend fun getAllExpense() : List<Expense>

    suspend fun getExpenses(category: String) : List<Expense>

    suspend fun addExpense(expense: Expense) : Long

    suspend fun deleteExpense(expense: Expense)

    suspend fun updateExpense(expense: Expense)


}