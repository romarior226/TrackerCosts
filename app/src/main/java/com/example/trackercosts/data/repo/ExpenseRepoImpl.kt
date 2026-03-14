package com.example.trackercosts.data.repo

import com.example.trackercosts.data.dao.ExpenseDao
import com.example.trackercosts.data.entity.ExpenseDbModel
import com.example.trackercosts.data.toDbModel
import com.example.trackercosts.data.toExpense
import com.example.trackercosts.domain.entity.Expense
import com.example.trackercosts.domain.repo.ExpenseRepository
import javax.inject.Inject

class ExpenseRepoImpl @Inject constructor(private val expanseDao: ExpenseDao) : ExpenseRepository {

    suspend fun getAllExpenseDbModelList(): List<ExpenseDbModel> {
        return expanseDao.getAllExpense()
    }

    override suspend fun getExpensesDetails(
        category: String,
        sortType: Byte,
        dateFrom: Long,
        dateTo: Long
    ): List<Expense> {
        return expanseDao.getExpensesDetails(category, sortType, dateFrom, dateTo)
            .map { it.toExpense()
        }
    }


    override suspend fun getAllExpense(): List<Expense> {
        return getAllExpenseDbModelList().map {
            it.toExpense()
        }
    }

    override suspend fun getExpenses(
        category: String,
        sortType: Byte
    ): List<Expense> {
        return expanseDao.getExpenses(category, sortType).map {
            it.toExpense()
        }
    }

    override suspend fun addExpense(expense: Expense): Long {
        return expanseDao.insertExpense(expense.toDbModel())
    }

    override suspend fun deleteExpense(expense: Expense) {
        expanseDao.deleteExpense(expense.toDbModel())
    }

    override suspend fun updateExpense(expense: Expense) {
        expanseDao.updateExpense(expense.toDbModel())
    }

}