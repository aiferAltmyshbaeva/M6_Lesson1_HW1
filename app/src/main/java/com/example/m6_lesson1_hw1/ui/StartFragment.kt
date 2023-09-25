package com.example.m6_lesson1_hw1.ui

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.m6_lesson1_hw1.R
import com.example.m6_lesson1_hw1.core.base.BaseFragment
import com.example.m6_lesson1_hw1.databinding.FragmentStartBinding
import com.example.m6_lesson1_hw1.ui.task.adapter.OnTaskClickListener
import com.example.m6_lesson1_hw1.ui.task.adapter.TaskAdapter
import com.example.m6_lesson1_hw1.viewmodel.TaskViewModel

class StartFragment : BaseFragment<
        FragmentStartBinding,
        TaskViewModel
        >(FragmentStartBinding::inflate) {

    override val viewModel: TaskViewModel by activityViewModels()

    override fun initView() {
        super.initView()
        val adapter = TaskAdapter(viewModel, object : OnTaskClickListener {
            override fun OnTaskClick(taskId: Int) {
                val bundle = bundleOf("taskId" to taskId)
                findNavController().navigate(R.id.action_startFragment_to_taskFragment, bundle)
            }
        })

        binding.recyclerView.adapter = adapter

        viewModel.filteredTasks.observe(viewLifecycleOwner) { tasks -> adapter.tasks = tasks }

        binding.fabAddTask.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_taskFragment)
        }

        val spinner: Spinner = binding.spinnerFilter
        ArrayAdapter.createFromResource(
            requireContext(), R.array.filter_options, android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selected = parent?.getItemAtPosition(position).toString()
                viewModel.setFilter(selected)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }
        viewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            spinner.visibility = if (tasks.isNullOrEmpty()) View.GONE else View.VISIBLE
        }
    }
}