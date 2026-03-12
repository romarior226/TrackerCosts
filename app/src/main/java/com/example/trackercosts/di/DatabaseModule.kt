package com.example.trackercosts.di

import android.content.Context
import com.example.trackercosts.data.dao.ExpenseDao
import com.example.trackercosts.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesDataBase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDataBase(context)
    }

    @Provides
    fun providesExpenseDao(database: AppDatabase): ExpenseDao {
        return database.expenseDao()
    }


}