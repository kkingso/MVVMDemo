package com.kkw.mymvvm.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkw.mymvvm.logic.model.GroupListEntity
import com.kkw.mymvvm.logic.model.ResponseData
import com.kkw.mymvvm.logic.network.Repository
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    private var liveData: MutableLiveData<ResponseData<GroupListEntity>>? = null

    suspend fun getGroupInfo(token: String, start: Int): MutableLiveData<ResponseData<GroupListEntity>>? =
        withContext(Dispatchers.IO) {
            liveData = Repository.getGroup(token, start)
            return@withContext liveData
        }
}