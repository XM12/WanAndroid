package com.gaia.wanandroid.base

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.gaia.wanandroid.databinding.ActivityBaseBinding
import com.gaia.wanandroid.ui.SplashActivity
import com.google.android.material.appbar.MaterialToolbar

abstract class BaseActivity<T : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {
    private val TAG = "BaseActivity"
    protected lateinit var binding: T
    protected lateinit var viewModel: VM
    protected lateinit var toolBar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val baseBinding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(baseBinding.root)
        toolBar = baseBinding.toolbar
        binding = getViewBinding()
        baseBinding.rootView.addView(binding.root)
        providerVMClass()?.let {
            viewModel = ViewModelProvider(this)[it]
        }
        //隐藏状态栏和toolbar
        if (javaClass.simpleName == SplashActivity::class.simpleName) {
            baseBinding.toolbar.visibility = View.GONE
            window.statusBarColor = Color.TRANSPARENT
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
        baseBinding.tvTitle.text = title
        initView()
    }

    abstract fun providerVMClass(): Class<VM>?

    abstract fun getViewBinding(): T

    abstract fun initView()

}