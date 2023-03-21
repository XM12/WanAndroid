package com.gaia.wanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding, VM : BaseViewModel> : Fragment() {
    protected lateinit var binding: T
    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        providerVMClass()?.let {
            viewModel = ViewModelProvider(this)[it]
        }
        binding = getViewBinding(inflater, container, savedInstanceState)
        initView()
        initData()
        return binding.root
    }

    abstract fun providerVMClass(): Class<VM>?

    abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): T

    abstract fun initView()

    open fun initData(){

    }

}