package com.gaia.wanandroid.net

import com.gaia.wanandroid.bean.*
import retrofit2.http.*

interface RetrofitService {

    @POST("/user/register")
    @FormUrlEncoded
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): BaseBean<UserBean>

    @POST("/user/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): BaseBean<UserBean>

    @GET("/banner/json")
    suspend fun getBanner(): BaseListBean<BannerBean>

    @GET("/article/list/{page}/json")
    suspend fun getHomeList(@Path("page") page: Int): BaseBean<HomeListBean>
}