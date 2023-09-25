package com.example.m6_lesson1_hw1.ui.task

import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.m6_lesson1_hw1.R
import com.example.m6_lesson1_hw1.core.base.BaseFragment
import com.example.m6_lesson1_hw1.databinding.FragmentTaskBinding
import com.example.m6_lesson1_hw1.model.TaskModel
import com.example.m6_lesson1_hw1.viewmodel.TaskViewModel

class TaskFragment : BaseFragment<
        FragmentTaskBinding,
        TaskViewModel
        >(FragmentTaskBinding::inflate) {

    override val viewModel: TaskViewModel by activityViewModels()

    private var editingTask: TaskModel? = null

    override fun initView() {
        super.initView()
        arguments?.getInt("taskId")?.let { taskId ->
            editingTask = viewModel.getTask(taskId)
            binding.etTitle.setText(editingTask?.title)
            binding.checkBox.isChecked = editingTask?.isCompleted ?: false
            binding.checkBox.visibility = View.VISIBLE
            binding.btnSave.text = getString(R.string.update)
        }
    }

    override fun initListener() {
        super.initListener()
        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            if (title.isNotBlank()) {
                if (editingTask != null) {
                    val updatedTask =
                        editingTask!!.copy(title = title, isCompleted = binding.checkBox.isChecked)
                    viewModel.updateTask(updatedTask)
                } else {
                    val newTask = TaskModel(id = System.currentTimeMillis().toInt(), title = title)
                    viewModel.addTask(newTask)
                }
                findNavController().popBackStack()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.title_cannot_be_empty), Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}
