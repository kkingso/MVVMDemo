package com.kkw.mymvvm.logic.model

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

/**
 * 封装网络请求的基础类
 */
data class ResponseData<T>(

    @SerializedName(value = "status", alternate = ["code"])
    val status: Int = 0,

    @SerializedName("errcode")
    val errorCode: String? = null,  // status=0时才返回

    @SerializedName("message")
    val errorMsg: String? = null, //  status=0时才返回

    @Nullable
    @SerializedName("data")
    var data: T? = null,

)

//记录接口状态的枚举
enum class AppState {
    LOADING, SUCCESS, ERROR, EMPTY
}
