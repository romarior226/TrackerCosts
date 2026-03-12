package com.example.trackercosts.domain.usecases

import com.example.trackercosts.domain.entity.Expense
import com.example.trackercosts.domain.repo.ExpenseRepository
import javax.inject.Inject

class UpdateExpenseUseCase  @Inject constructor(private val expenseRepository: ExpenseRepository) {
    suspend operator fun invoke(expense: Expense) {
        expenseRepository.updateExpense(expense)
    }
}