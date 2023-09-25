package com.example.m6_lesson1_hw1.ui.task.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.m6_lesson1_hw1.R
import com.example.m6_lesson1_hw1.databinding.ItemTaskBinding
import com.example.m6_lesson1_hw1.model.TaskModel
import com.example.m6_lesson1_hw1.viewmodel.TaskViewModel

class TaskAdapter(private val viewModel: TaskViewModel, private val listener: OnTaskClickListener) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    inner class TaskViewHolder(val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root)


    var tasks = listOf<TaskModel>()
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

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

        holder.binding.apply {
            checkBox.setOnCheckedChangeListener(null)
            checkBox.isChecked = task.isCompleted
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (task.isCompleted != isChecked) {
                    viewModel.toggleTask(task.id)
                }
            }
            tvTitle.text = task.title
            root.setOnLongClickListener {
                AlertDialog.Builder(it.context)
                    .setMessage(it.context.getString(R.string.delete_task))
                    .setPositiveButton(it.context.getString(R.string.yes)) { _, _ ->
                        viewModel.deleteTask(task.id)
                    }
                    .setNegativeButton(it.context.getString(R.string.no), null)
                    .show()
                true
            }
            root.setOnClickListener {
                listener.OnTaskClick(task.id)
            }
        }
    }
}


