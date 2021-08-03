package com.kkw.mymvvm.logic.network

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kkw.mymvvm.logic.model.ResponseData

/**
 * MutableLiveData 的扩展方法，用于处理网络请求的结果
 */
fun <T> MutableLiveData<ResponseData<T>>.appObserve(
    activity: Activity, onSuccess: (T) -> Unit, onError: () -> Unit = {}
) {
    this.observe(activity as LifecycleOwner, Observer {
        when (it.status) {
            1 -> {
                val data = it.data
                if (data != null) {
                    onSuccess(data)
                }
            }
            0 -> onError()
        }
    })
}
