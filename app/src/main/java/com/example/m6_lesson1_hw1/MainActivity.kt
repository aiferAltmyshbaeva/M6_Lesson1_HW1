package com.example.m6_lesson1_hw1

import com.example.m6_lesson1_hw1.core.base.BaseActivity
import com.example.m6_lesson1_hw1.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateViewBinding() = ActivityMainBinding.inflate(layoutInflater)

}