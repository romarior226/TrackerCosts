package com.example.trackercosts.domain.usecases

import com.example.trackercosts.domain.entity.Expense
import com.example.trackercosts.domain.repo.ExpenseRepository
import javax.inject.Inject

class GetExpensesDetailsUseCase @Inject constructor(private val expenseRepository: ExpenseRepository) {
    suspend operator fun invoke(
        category: String,
        sortType: Byte,
        dateFrom: Long,
        dateTo: Long
    ): List<Expense> {
        return expenseRepository.getExpensesDetails(category, sortType, dateFrom, dateTo)
    }
}