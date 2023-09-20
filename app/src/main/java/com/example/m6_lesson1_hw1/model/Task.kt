package com.example.m6_lesson1_hw1.model

data class Task(
    val id: Int,
    var title: String,
    var isCompleted: Boolean = false
)
