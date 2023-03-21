package com.gaia.wanandroid.ui

import android.content.Intent
import android.widget.Toast
import com.gaia.wanandroid.base.BaseActivity
import com.gaia.wanandroid.databinding.ActivityLoginBinding
import com.gaia.wanandroid.viewmodel.UserViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, UserViewModel>() {

    override fun providerVMClass(): Class<UserViewModel> {
        return UserViewModel::class.java
    }

    override fun getViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun initView() {
        viewModel.userInfo.observe(this) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.btnLogin.setOnClickListener {
            val userName = binding.inputName.text.toString()
            val password = binding.inputPassword.text.toString()
            if (userName.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(userName, password)
            } else {
                Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show()
            }
        }
    }
}