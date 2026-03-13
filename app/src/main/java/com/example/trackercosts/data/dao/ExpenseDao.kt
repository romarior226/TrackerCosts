package com.example.trackercosts.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.trackercosts.data.entity.ExpenseDbModel
import com.example.trackercosts.domain.entity.Category
import com.example.trackercosts.domain.entity.Expense

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expense ORDER BY date DESC")
    suspend fun getAllExpense() : List<ExpenseDbModel>

    @Query("""
    SELECT * FROM expense 
    WHERE :category = 'ALL' OR category = :category
    ORDER BY date DESC
    """)
    suspend fun getExpenses(category: String) : List<ExpenseDbModel>


    @Insert
    suspend fun insertExpense(expenseDbModel: ExpenseDbModel ) : Long

    @Update
    suspend fun updateExpense(expenseDbModel: ExpenseDbModel)

    @Delete
    suspend fun deleteExpense(expenseDbModel: ExpenseDbModel)

}