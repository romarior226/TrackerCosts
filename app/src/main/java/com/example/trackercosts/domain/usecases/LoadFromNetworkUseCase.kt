package com.example.trackercosts.domain.usecases

import com.example.trackercosts.domain.repo.ExpenseRepository
import com.example.trackercosts.domain.repo.ExpenseRepositoryNetwork
import javax.inject.Inject

class LoadFromNetworkUseCase @Inject constructor(
    private val expenseRepositoryNetwork: ExpenseRepositoryNetwork,
    private val expenseRepository: ExpenseRepository
) {
    suspend operator fun invoke() {
        val count = expenseRepository.getAllExpense().count()
        if (count == 0) {
        val expenseFromNetwork =expenseRepositoryNetwork.getExpanseNetwork()
        expenseFromNetwork.forEach {
            expenseRepository.addExpense(it)
        }
    }
    }
}