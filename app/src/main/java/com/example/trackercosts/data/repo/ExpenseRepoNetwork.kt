package com.example.trackercosts.data.repo

import com.example.trackercosts.data.network.RetrofitInstance
import com.example.trackercosts.data.toExpense
import com.example.trackercosts.domain.entity.Expense
import com.example.trackercosts.domain.repo.ExpenseRepositoryNetwork
import javax.inject.Inject

class ExpenseRepoNetwork @Inject constructor() : ExpenseRepositoryNetwork {
    override suspend fun getExpanseNetwork(): List<Expense> {
        return RetrofitInstance.api.getExpanse(50).map {
            it.toExpense()
        }
    }

}