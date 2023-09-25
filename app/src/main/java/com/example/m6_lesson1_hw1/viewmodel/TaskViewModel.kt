package com.example.m6_lesson1_hw1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.m6_lesson1_hw1.model.TaskModel

class TaskViewModel : ViewModel() {

    private val _tasks = MutableLiveData<MutableList<TaskModel>>()
    val tasks: LiveData<MutableList<TaskModel>> get() = _tasks

    private val _filteredTasks = MutableLiveData<List<TaskModel>>()
    val filteredTasks: LiveData<List<TaskModel>> get() = _filteredTasks

    init {
        _tasks.value = mutableListOf()
    }

    fun addTask(task: TaskModel) {
        _tasks.value?.add(task)
        _tasks.postValue(_tasks.value)
    }

    fun toggleTask(taskId: Int) {
        val task = _tasks.value?.find { it.id == taskId }
        task?.isCompleted = task?.isCompleted != true
        _tasks.postValue(_tasks.value)
    }

    fun deleteTask(taskId: Int) {
        _tasks.value?.removeIf { it.id == taskId }
        _tasks.postValue(_tasks.value)
    }

    fun getTask(taskId: Int): TaskModel? {
        return _tasks.value?.find { it.id == taskId }
    }

    fun updateTask(updatedTask: TaskModel) {
        _tasks.value?.find { it.id == updatedTask.id }?.apply {
            title = updatedTask.title
            isCompleted = updatedTask.isCompleted
        }
        _tasks.postValue(_tasks.value)
    }

    fun setFilter(filter: String) {
        _filteredTasks.value = when (filter) {
            "Completed Tasks" -> _tasks.value?.filter { it.isCompleted }
            "Uncompleted Tasks" -> _tasks.value?.filter { !it.isCompleted }
            else -> _tasks.value
        }
    }
}