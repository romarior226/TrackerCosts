package com.example.trackercosts.di

import com.example.trackercosts.data.repo.ExpenseRepoImpl
import com.example.trackercosts.data.repo.ExpenseRepoNetwork
import com.example.trackercosts.domain.repo.ExpenseRepository
import com.example.trackercosts.domain.repo.ExpenseRepositoryNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindExpenseRepository(
        expenseRepoImpl: ExpenseRepoImpl
    ): ExpenseRepository

    @Binds
    abstract fun bindExpenseRepositoryNetwork(
        expenseRepoImpl: ExpenseRepoNetwork
    ): ExpenseRepositoryNetwork
}