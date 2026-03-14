package com.example.trackercosts.presentation

import android.app.DatePickerDialog
import android.icu.util.Calendar
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
import com.example.trackercosts.databinding.TrackerFragmentSearchBinding
import com.example.trackercosts.domain.entity.Category
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExpenseFragmentSearch : Fragment(R.layout.tracker_fragment_search) {
    private val viewmodel: ExpenseViewModel by activityViewModels()

    val adapter = ExpenseAdapter(
        { expense -> viewmodel.deleteExpanse(expense) },
        { expense ->
            val bundle = Bundle().apply { putInt("expense_id", expense.id) }
            findNavController().navigate(R.id.action_expenseFragment_to_addExpense, bundle)
            viewmodel.updateExpense(expense)
        })

    private
    var _binding: TrackerFragmentSearchBinding? = null
    val binding: TrackerFragmentSearchBinding
        get() = _binding ?: throw RuntimeException("TrackerTemExpenseBinding")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TrackerFragmentSearchBinding.inflate(inflater, container, false)
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
            spinnerCategory.adapter = categoryAdapter
            spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val category = spinnerCategory.selectedItem as Category
                    viewmodel.changeCategory(category)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
            var sortTypeAmount = 0.toByte()
            btnSortAmount.setOnClickListener {
                sortTypeAmount = if (sortTypeAmount == 0.toByte()) 1
                else 0
                viewmodel.changeSortByAmount(sortTypeAmount)
            }
            var sortTypeDate = 0.toByte()
            btnSortDate.setOnClickListener {
                sortTypeDate = if (sortTypeDate == 0.toByte()) 1
                else 0
                viewmodel.changeSortByDate(sortTypeDate)
            }
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            btnDateFrom.setOnClickListener {
                DatePickerDialog(requireContext(), { _, y, m, d ->
                    val cal = Calendar.getInstance()
                    cal.set(y, m, d, 0, 0, 0)
                    viewmodel.changeDateFrom(cal.timeInMillis)
                    btnDateFrom.text = "$d.${m + 1}.$y"
                }, year, month, day).show()
            }
            btnDateTo.setOnClickListener {
                DatePickerDialog(requireContext(), { _, y, m, d ->
                    val cal = Calendar.getInstance()
                    cal.set(y, m, d, 23, 59, 59)
                    viewmodel.changeDateTo(cal.timeInMillis)
                    btnDateTo.text = "$d.${m + 1}.$y"
                }, year, month, day).show()
            }
            btnSearch.setOnClickListener {
                viewmodel.getExpenseDetailed()
            }
            rvSearchResults.adapter = adapter
            rvSearchResults.layoutManager = LinearLayoutManager(requireContext())

        }
    }
}