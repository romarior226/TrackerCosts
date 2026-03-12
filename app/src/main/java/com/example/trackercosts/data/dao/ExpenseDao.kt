package com.example.trackercosts.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.trackercosts.data.entity.ExpenseDbModel
import com.example.trackercosts.domain.entity.Expense

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expense")
    suspend fun getAllExpense() : List<ExpenseDbModel>

    @Insert
    suspend fun insertExpense(expenseDbModel: ExpenseDbModel ) : Long

    @Update
    suspend fun updateExpense(expenseDbModel: ExpenseDbModel)

    @Delete
    suspend fun deleteExpense(expenseDbModel: ExpenseDbModel)

}