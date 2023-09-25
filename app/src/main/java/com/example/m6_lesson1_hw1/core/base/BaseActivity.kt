package com.example.m6_lesson1_hw1.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<
        VB : ViewBinding
        > : AppCompatActivity() {

    protected lateinit var binding: VB
    protected abstract fun inflateViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding()
        setContentView(binding.root)

        initInternetConnection()
        initView()
        initListener()
    }

    open fun initInternetConnection() {}

    open fun initView() {}

    open fun initListener() {}
}