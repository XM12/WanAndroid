package com.gaia.wanandroid.bean

data class UserBean(
    var id: Int,
    var username: String,
    var password: String,
    var icon: String?,
    var type: Int,
    var collectIds: List<Int>?
)
