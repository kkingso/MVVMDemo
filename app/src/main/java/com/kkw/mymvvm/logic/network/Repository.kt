package com.kkw.mymvvm.logic.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.kkw.mymvvm.logic.model.GroupListEntity
import com.kkw.mymvvm.logic.model.ResponseData
import com.kkw.mymvvm.logic.network.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 *  仓库层
 * 处理数据请求
 */
object Repository {

    private const val TAG = "Repository"
    private val mService = RetrofitBuilder.create(ApiService::class.java)

    suspend fun <T> Call<ResponseData<T>>.await(): ResponseData<T> {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<ResponseData<T>> {
                override fun onResponse(
                    call: Call<ResponseData<T>?>, response: Response<ResponseData<T>?>
                ) {
                    Log.i(TAG,"response-->${response.raw()}")
                    val responseData = response.body()
                    if (responseData?.status == 1){
                        continuation.resume(responseData)
                    }
                }

                override fun onFailure(call: Call<ResponseData<T>>, t: Throwable) {
                    Log.i(TAG, "onFailure-->${call.request()}", t)
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    suspend fun getGroup(token: String?,start:Int): MutableLiveData<ResponseData<GroupListEntity>> {

        val liveData: MutableLiveData<ResponseData<GroupListEntity>> =
            MutableLiveData<ResponseData<GroupListEntity>>()

        val response = mService.getZDaoGroups(token,start,20).await()
        Log.e("kkww","response: ${response}")
        liveData.postValue(response)
        return liveData
    }

}