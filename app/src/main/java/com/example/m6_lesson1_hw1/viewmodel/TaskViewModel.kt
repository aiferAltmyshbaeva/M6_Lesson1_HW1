package com.example.m6_lesson1_hw1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.m6_lesson1_hw1.model.Task

class TaskViewModel : ViewModel() {

    private val _tasks = MutableLiveData<MutableList<Task>>()
    val tasks: LiveData<MutableList<Task>> get() = _tasks

    init {
        _tasks.value = mutableListOf()
    }

    fun addTask(task: Task) {
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
}