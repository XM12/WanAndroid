package com.gaia.wanandroid.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gaia.wanandroid.base.BaseViewModel
import com.gaia.wanandroid.bean.UserBean
import com.gaia.wanandroid.constant.Constant
import com.gaia.wanandroid.net.RetrofitClient
import com.gaia.wanandroid.utils.DataStoreUtils

class UserViewModel : BaseViewModel() {
    val userInfo: MutableLiveData<UserBean> by lazy { MutableLiveData<UserBean>() }

    fun register() {
        launchUI {
            request {
                RetrofitClient.instance.service.register("爆的Gaia", "123456", "123456")
            }?.apply {
                Log.e(Constant.HTTP_TAG, "register: $this")
            } ?: apply {
                Log.e(Constant.HTTP_TAG, "register: data is null")
            }
        }
    }

    fun login(userName: String, password: String) {
        launchUI {
            request {
                RetrofitClient.instance.service.login(userName, password)
            }?.apply {
                userInfo.value = this
                DataStoreUtils.putData(Constant.USER_ID, id)
                DataStoreUtils.putData(Constant.USER_NAME, username)
                DataStoreUtils.putData(Constant.USER_PASSWORD, password)
            }
        }
    }
}