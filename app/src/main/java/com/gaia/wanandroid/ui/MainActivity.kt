package com.gaia.wanandroid.ui

import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.gaia.wanandroid.R
import com.gaia.wanandroid.base.BaseActivity
import com.gaia.wanandroid.base.BaseViewModel
import com.gaia.wanandroid.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {

    override fun providerVMClass(): Class<BaseViewModel>? {
        return null
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_arch))
        toolBar.setupWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}