package com.gaia.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gaia.wanandroid.base.BaseFragment
import com.gaia.wanandroid.databinding.FragmentArchBinding
import com.gaia.wanandroid.viewmodel.ArchViewModel

class ArchFragment : BaseFragment<FragmentArchBinding, ArchViewModel>() {

    override fun providerVMClass(): Class<ArchViewModel> {
        return ArchViewModel::class.java
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentArchBinding {
        return FragmentArchBinding.inflate(inflater, container, false)
    }

    override fun initView() {

    }
}