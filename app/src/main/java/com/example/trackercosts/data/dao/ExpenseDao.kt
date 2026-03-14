package com.example.trackercosts.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.trackercosts.data.entity.ExpenseDbModel

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expense ORDER BY date DESC")
    suspend fun getAllExpense(): List<ExpenseDbModel>

    @Query(
        """
    SELECT * FROM expense 
    WHERE (:category = 'ALL' OR category = :category)
ORDER BY
    CASE WHEN :sortType = 0 THEN date END DESC,
    CASE WHEN :sortType = 1 THEN date END ASC,
    CASE WHEN :sortType = 2 THEN amount END DESC,
    CASE WHEN :sortType = 3 THEN amount END ASC
"""
    )
    suspend fun getExpenses(
        category: String,
        sortType: Byte,
    ): List<ExpenseDbModel>

    @Query(
        """
    SELECT * FROM expense 
    WHERE (:category = 'ALL' OR category = :category)
    AND(date BETWEEN :dateFrom AND :dateTo )
ORDER BY
    CASE WHEN :sortType = 0 THEN date END DESC,
    CASE WHEN :sortType = 1 THEN date END ASC,
    CASE WHEN :sortType = 2 THEN amount END DESC,
    CASE WHEN :sortType = 3 THEN amount END ASC
"""
    )
    suspend fun getExpensesDetails(
        category: String,
        sortType: Byte,
        dateFrom: Long,
        dateTo: Long
    ): List<ExpenseDbModel>


    @Insert
    suspend fun insertExpense(expenseDbModel: ExpenseDbModel): Long

    @Update
    suspend fun updateExpense(expenseDbModel: ExpenseDbModel)

    @Delete
    suspend fun deleteExpense(expenseDbModel: ExpenseDbModel)

}