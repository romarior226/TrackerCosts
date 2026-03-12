package com.example.trackercosts.domain.usecases

import com.example.trackercosts.domain.entity.Category
import com.example.trackercosts.domain.entity.Expense
import com.example.trackercosts.domain.repo.ExpenseRepository
import javax.inject.Inject

class AddExpenseUseCase @Inject constructor(private val expenseRepository: ExpenseRepository) {
    suspend operator fun invoke(
        amount: Double,
        category: Category,
        description: String,
        currency: String
    ): Expense {
        val expense = Expense(
            id = 0,
            amount = amount,
            category = category,
            description = description,
            date = System.currentTimeMillis(),
            currency = currency
        )
        val generatedId = expenseRepository.addExpense(expense)
        return expense.copy(id = generatedId.toInt())
    }
}
