package com.gaia.wanandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gaia.wanandroid.base.BaseActivity
import com.gaia.wanandroid.databinding.ActivityRegisterBinding
import com.gaia.wanandroid.viewmodel.UserViewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding, UserViewModel>() {

    override fun providerVMClass(): Class<UserViewModel> {
        return UserViewModel::class.java
    }

    override fun getViewBinding(): ActivityRegisterBinding {
        return ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun initView() {

    }

}