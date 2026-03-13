package com.example.trackercosts.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trackercosts.R
import com.example.trackercosts.databinding.TrackerFragmentExpenseListBinding
import com.example.trackercosts.domain.entity.Category
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.apply

@AndroidEntryPoint
class ExpenseFragment : Fragment(R.layout.tracker_fragment_expense_list) {

    private val viewmodel: ExpenseViewModel by activityViewModels()

    val adapter = ExpenseAdapter(
        { expense -> viewmodel.deleteExpanse(expense) },
        { expense ->
            val bundle = Bundle().apply {
                putInt("expense_id", expense.id)
            }
            findNavController().navigate(R.id.action_expenseFragment_to_addExpense, bundle)
            viewmodel.updateExpense(expense)
        })

    private
    var _binding: TrackerFragmentExpenseListBinding? = null
    val binding: TrackerFragmentExpenseListBinding
        get() = _binding ?: throw RuntimeException("TrackerTemExpenseBinding")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TrackerFragmentExpenseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.expanseList.collect {
                adapter.submitList(it)
            }
        }
        val categoryAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            Category.entries
        )
        with(binding) {
            categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerFilterCategory.adapter = categoryAdapter
            spinnerFilterCategory.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val category = spinnerFilterCategory.selectedItem as Category
                        viewmodel.changeCategory(category)
                        viewmodel.getExpense()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
            var sortByDate: Byte = 0
            btnSortDate.setOnClickListener {
                sortByDate = if (sortByDate == 0.toByte()) 1
                else 0
                viewmodel.changeSortByDate(sortByDate)
                viewmodel.getExpense()
            }
            var sortByAmount: Byte = 2
            btnSortAmount.setOnClickListener {
                sortByAmount = if (sortByAmount == 2.toByte()) 3
                else 2
                viewmodel.changeSortByAmount(sortByAmount)
                viewmodel.getExpense()
            }
            rvExpenses.adapter = adapter
            rvExpenses.layoutManager = LinearLayoutManager(requireContext())
            binding.fabAddExpense.setOnClickListener {
                findNavController().navigate(R.id.action_expenseFragment_to_addExpense)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}