package com.example.trackercosts.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trackercosts.R
import com.example.trackercosts.databinding.TrackerFragmentAddExpenseBinding
import com.example.trackercosts.databinding.TrackerFragmentExpenseListBinding
import com.example.trackercosts.domain.entity.Category
import com.example.trackercosts.domain.entity.Expense
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue
import kotlin.math.exp


@AndroidEntryPoint
class ExpenseFragmentAddExpense : Fragment(R.layout.tracker_fragment_add_expense) {
    private val viewmodel: ExpenseViewModel by activityViewModels()


    private var _binding: TrackerFragmentAddExpenseBinding? = null
    val binding: TrackerFragmentAddExpenseBinding
        get() = _binding ?: throw RuntimeException("TrackerTemExpenseBinding")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TrackerFragmentAddExpenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val expenseId = arguments?.getInt("expense_id")
        val categoryAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            Category.entries
        )
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = categoryAdapter
        val currencies = listOf("UAH", "USD", "EUR")
        val currencyAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            currencies
        )
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCurrency.adapter = currencyAdapter
        with(binding) {
            if (expenseId != null) {
                val expense = viewmodel.expanseList.value.find { it.id == expenseId }
                    ?: throw RuntimeException("NO SUCH ID")
                etAmount.setText(expense.amount.toString())
                etDescription.setText(expense.description)
                val positionCategory = categoryAdapter.getPosition(expense.category)
                spinnerCategory.setSelection(positionCategory)
                val positionCurrency = currencyAdapter.getPosition(expense.currency)
                spinnerCurrency.setSelection(positionCurrency)
                btnSave.setOnClickListener {
                    val updatedExpense = expense.copy(
                        amount = etAmount.text.toString().toDouble(),
                        description = etDescription.text.toString(),
                        currency = spinnerCurrency.selectedItem.toString(),
                        category = spinnerCategory.selectedItem as Category
                    )
                    viewmodel.updateExpense(updatedExpense)
                    findNavController().popBackStack()
                }
            } else {
                binding.btnSave.setOnClickListener {
                    val amount = etAmount.text.toString().toDouble()
                    val description = etDescription.text.toString()
                    val currency = spinnerCurrency.selectedItem.toString()
                    Log.d("currencyAdapter", currency)
                    val category = spinnerCategory.selectedItem as Category
                    viewmodel.addExpense(amount, category, description, currency)
                    findNavController().popBackStack()

                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

