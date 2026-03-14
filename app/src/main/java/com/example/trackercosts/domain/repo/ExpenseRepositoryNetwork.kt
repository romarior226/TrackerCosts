package com.example.trackercosts.domain.repo

import com.example.trackercosts.domain.entity.Expense

interface ExpenseRepositoryNetwork  {
    suspend fun getExpanseNetwork(): List<Expense>
}