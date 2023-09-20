package com.example.m6_lesson1_hw1.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.m6_lesson1_hw1.R
import com.example.m6_lesson1_hw1.databinding.FragmentStartBinding
import com.example.m6_lesson1_hw1.ui.task.adapter.TaskAdapter
import com.example.m6_lesson1_hw1.viewmodel.TaskViewModel

class StartFragment : Fragment(R.layout.fragment_start) {

    private lateinit var binding: FragmentStartBinding
    private val viewModel: TaskViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStartBinding.bind(view)

        val adapter = TaskAdapter(viewModel)
        binding.recyclerView.adapter = adapter

        viewModel.tasks.observe(viewLifecycleOwner) { tasks -> adapter.tasks = tasks }

        binding.fabAddTask.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_taskFragment)
        }
    }

}