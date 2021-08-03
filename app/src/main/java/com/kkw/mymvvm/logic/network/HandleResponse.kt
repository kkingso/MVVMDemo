package com.kkw.mymvvm.logic.network

import CallBackLoad
import android.accounts.NetworkErrorException
import android.util.Log
import com.kkw.mymvvm.logic.model.ResponseData
import retrofit2.Response

object HandleResponse {

    private const val TAG = "HandleResponse"

    @JvmStatic
    fun <T> handleResponse(callBackLoad: CallBackLoad<T>,response: Response<ResponseData<T>?>) {
        Log.e(TAG,"response-->${response.raw()}")
        if (callBackLoad == null){
            return
        }
        val httpCode = response.code()
        if (response.isSuccessful){
            val responseData = response.body()
            if (responseData?.status == 1){  // loadSuccess
                callBackLoad.loadSuccess(responseData)
            }else{
                val errorCode = responseData?.errorCode
                if (errorCode !=null){  // loadError

                }else{  // loadFailure
                    callBackLoad.loadFailure(NetworkErrorException(errorCode.toString()))
                }
            }
        }else{

        }
    }

    @JvmStatic
    fun <T> handleResponse(response: Response<ResponseData<T>?>) {
        Log.e(TAG,"response-->${response.raw()}")
        val httpCode = response.code()
        if (response.isSuccessful){
            val responseData = response.body()
            if (responseData?.status == 1){  // loadSuccess
//                callBackLoad.loadSuccess(responseData)
            }else{
                val errorCode = responseData?.errorCode
                if (errorCode !=null){  // loadError

                }else{  // loadFailure
//                    callBackLoad.loadFailure(NetworkErrorException(errorCode.toString()))
                }
            }
        }else{

        }
    }
}