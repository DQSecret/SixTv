package com.example.sixtv.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sixtv.network.RetrofitExt.Api
import com.example.sixtv.network.Tv
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val mResultContentObs = MutableLiveData<List<Tv>>()
    val mResultStatusObs = MutableLiveData<String>()

    fun getTvs() = viewModelScope.launch {
        mResultStatusObs.postValue("请求最新数据中...")
        kotlin.runCatching {
            val repo = Api.getTvAsync()
            mResultContentObs.postValue(repo.data)
            mResultStatusObs.postValue("请求成功啦~")
        }.onFailure {
            mResultContentObs.postValue(emptyList())
            mResultStatusObs.postValue("请求失败了-${it.message}")
        }
    }
}