package com.example.m6_lesson1_hw1.ui.task

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.m6_lesson1_hw1.R
import com.example.m6_lesson1_hw1.databinding.FragmentTaskBinding
import com.example.m6_lesson1_hw1.model.Task
import com.example.m6_lesson1_hw1.viewmodel.TaskViewModel

class TaskFragment : Fragment(R.layout.fragment_task) {

    private lateinit var binding: FragmentTaskBinding
    private val viewModel: TaskViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTaskBinding.bind(view)

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            if (title.isNotBlank()) {
                val newTask = Task(id = System.currentTimeMillis().toInt(), title = title)
                viewModel.addTask(newTask)
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Title cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

    }

}