package com.example.trackercosts.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trackercosts.R
import com.example.trackercosts.databinding.TrackerFragmentExpenseListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExpenseFragment : Fragment(R.layout.tracker_fragment_expense_list) {

    private val viewmodel: ExpenseViewModel by activityViewModels()

    val adapter = ExpenseAdapter(
        { expense -> viewmodel.deleteExpanse(expense) }
    )
    private var _binding: TrackerFragmentExpenseListBinding? = null
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
        with(binding) {
            rvExpenses.adapter = adapter
            rvExpenses.layoutManager = LinearLayoutManager(requireContext())
            binding.fabAddExpense.setOnClickListener {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}