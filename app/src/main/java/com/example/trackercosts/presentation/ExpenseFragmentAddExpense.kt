package com.example.trackercosts.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trackercosts.R
import com.example.trackercosts.databinding.TrackerFragmentAddExpenseBinding
import com.example.trackercosts.databinding.TrackerFragmentExpenseListBinding
import com.example.trackercosts.domain.entity.Category
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue


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
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            binding.btnSave.setOnClickListener {
                val Amount = etAmount.toString().toInt()
                val s = binding.tilAmount
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

