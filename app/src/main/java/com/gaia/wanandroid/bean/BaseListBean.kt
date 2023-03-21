package com.gaia.wanandroid.bean

data class BaseListBean<T>(
    var errorCode: Int,
    var errorMsg: String?,
    var data: MutableList<T>?
)
