package com.example.trackercosts.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackercosts.domain.entity.Category
import com.example.trackercosts.domain.entity.Expense
import com.example.trackercosts.domain.usecases.AddExpenseUseCase
import com.example.trackercosts.domain.usecases.DeleteExpenseUseCase
import com.example.trackercosts.domain.usecases.GetAllExpenseUseCase
import com.example.trackercosts.domain.usecases.GetExpenseUseCase
import com.example.trackercosts.domain.usecases.UpdateExpenseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val addExpenseUseCase: AddExpenseUseCase,
    private val deleteExpenseUseCase: DeleteExpenseUseCase,
    private val getAllExpenseUseCase: GetAllExpenseUseCase,
    private val updateExpenseUseCase: UpdateExpenseUseCase,
    private val getExpenseUseCase: GetExpenseUseCase
) : ViewModel() {


    private var sortByType: Byte = 0
    private var currentCategory: Category = Category.ALL

    private val _expenseList = MutableStateFlow<List<Expense>>(emptyList())
    val expanseList: StateFlow<List<Expense>>
        get() = _expenseList


    init {
        loadExpense()
    }

    fun changeSortByDate(sortDate: Byte) {
        sortByType = sortDate
    }

    fun changeSortByAmount(sortAmount: Byte) {
        sortByType = sortAmount
    }

    fun changeCategory(category: Category) {
        currentCategory = category
    }

    fun getExpense(
    ) {
        viewModelScope.launch {
            _expenseList.value = getExpenseUseCase(currentCategory.name, sortByType)
        }
    }

    fun loadExpense() {
        viewModelScope.launch {
            _expenseList.value = getAllExpenseUseCase()
        }
    }

    fun addExpense(
        amount: Double,
        category: Category,
        description: String,
        currency: String
    ) {
        viewModelScope.launch {
            val expense = addExpenseUseCase(amount, category, description, currency)
            _expenseList.value += expense
        }
    }

    fun deleteExpanse(expense: Expense) {
        viewModelScope.launch {
            deleteExpenseUseCase(expense)
            _expenseList.value -= expense
        }
    }

    fun updateExpense(expense: Expense) {
        viewModelScope.launch {
            _expenseList.value = _expenseList.value.map {
                if (it.id == expense.id) expense
                else it
            }
            updateExpenseUseCase(expense)
        }
    }


}