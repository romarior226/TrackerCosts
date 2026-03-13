package com.example.trackercosts.domain.usecases

import com.example.trackercosts.domain.entity.Category
import com.example.trackercosts.domain.entity.Expense
import com.example.trackercosts.domain.repo.ExpenseRepository
import javax.inject.Inject

class GetExpenseUseCase @Inject constructor(private val expenseRepository: ExpenseRepository) {
    suspend operator fun invoke(
        category: String,
        sortType: Byte
    ): List<Expense> {
        return expenseRepository.getExpenses(category, sortType)
    }
}