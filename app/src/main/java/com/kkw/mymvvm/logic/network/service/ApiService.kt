package com.kkw.mymvvm.logic.network.service

import com.kkw.mymvvm.logic.model.GroupListEntity
import com.kkw.mymvvm.logic.model.ResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("zdim/groups?client_app=ZaoDao_Android%406.19.0&device_id=AID-a4c383632d51c2ed")
    fun getZDaoGroups(@Query("token") token: String?,@Query("start") start: Int,@Query("num") num: Int?): Call<ResponseData<GroupListEntity>>

    @GET("zdim/groups?client_app=ZaoDao_Android%406.19.0&device_id=AID-a4c383632d51c2ed")
    fun getToken(@Query("token") token: String?,@Query("start") start: Int,@Query("num") num: Int?): Call<ResponseData<GroupListEntity>>

}