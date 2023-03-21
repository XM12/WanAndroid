package com.gaia.wanandroid.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gaia.wanandroid.base.BaseActivity
import com.gaia.wanandroid.base.BaseViewModel
import com.gaia.wanandroid.constant.Constant
import com.gaia.wanandroid.databinding.ActivitySplashBinding
import com.gaia.wanandroid.utils.DataStoreUtils

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding, BaseViewModel>() {
    private val startUptimeMillis = SystemClock.uptimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        val contentView = findViewById<View>(android.R.id.content)
        val userName = DataStoreUtils.getData(Constant.USER_NAME, "")
        contentView.viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                return if (isReady()) {
                    contentView.viewTreeObserver.removeOnPreDrawListener(this)
                    this@SplashActivity.startActivity(
                        Intent(
                            this@SplashActivity,
                            if (userName.isNotEmpty()) MainActivity::class.java else LoginActivity::class.java
                        )
                    )
                    finish()
                    true
                } else {
                    false
                }
            }
        })
    }

    override fun providerVMClass(): Class<BaseViewModel>? {
        return null
    }

    override fun getViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    private fun isReady(): Boolean {
        return SystemClock.uptimeMillis() - startUptimeMillis > 1500
    }

    override fun initView() {

    }
}