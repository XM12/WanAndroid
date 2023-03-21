package com.gaia.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gaia.wanandroid.base.BaseViewModel
import com.gaia.wanandroid.bean.BannerBean
import com.gaia.wanandroid.bean.HomeListBean
import com.gaia.wanandroid.net.RetrofitClient

class HomeViewModel : BaseViewModel() {
    val bannerData: MutableLiveData<List<BannerBean>> by lazy { MutableLiveData<List<BannerBean>>() }
    val homeData: MutableLiveData<HomeListBean> by lazy { MutableLiveData<HomeListBean>() }

    fun getBinnerData() {
        launchUI {
            requestList {
                RetrofitClient.instance.service.getBanner()
            }?.apply {
                bannerData.value = this
            }
        }
    }

    /**
     * 请求首页列表数据
     * */
    fun loadHomeListData() {
        launchUI {
            request {
                RetrofitClient.instance.service.getHomeList(0)
            }?.apply {
                homeData.value = this
            }
        }
    }
}