package com.kkw.mymvvm.logic.model

import com.google.gson.annotations.SerializedName

data class Account(

    @SerializedName(value = "token", alternate = ["e"])
    private var token: String? = null //登录返回token

)
