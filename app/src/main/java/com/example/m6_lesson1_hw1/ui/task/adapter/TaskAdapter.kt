package com.example.m6_lesson1_hw1.ui.task.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.m6_lesson1_hw1.databinding.ItemTaskBinding
import com.example.m6_lesson1_hw1.model.Task
import com.example.m6_lesson1_hw1.viewmodel.TaskViewModel

class TaskAdapter(private val viewModel: TaskViewModel) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    var tasks = listOf<Task>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.binding.apply {
            checkBox.isChecked = task.isCompleted
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (task.isCompleted != isChecked) {
                    viewModel.toggleTask(task.id)
                }
            }
            tvTitle.text = task.title
            root.setOnLongClickListener {
                AlertDialog.Builder(it.context)
                    .setMessage("Delete Task")
                    .setPositiveButton("Yes") { _, _ ->
                        viewModel.deleteTask(task.id)
                    }
                    .setNegativeButton("No", null)
                    .show()
                true
            }
        }
    }

    override fun getItemCount() = tasks.size

    inner class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

}