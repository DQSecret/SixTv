package com.example.sixtv.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sixtv.network.Menu
import com.example.sixtv.network.RetrofitExt.Api
import kotlinx.coroutines.launch

class MenuDetailsViewModel : ViewModel() {

    val mResultContentObs = MutableLiveData<List<Menu>>()
    val mResultStatusObs = MutableLiveData<String>()

    fun getMenus(tvId: String) = viewModelScope.launch {
        mResultStatusObs.postValue("请求最新数据中...")
        kotlin.runCatching {
            val repo = Api.getMenuAsync(tvId).data?.menu ?: emptyList()
            mResultContentObs.postValue(repo)
            mResultStatusObs.postValue("请求成功啦~")
        }.onFailure {
            mResultContentObs.postValue(emptyList())
            mResultStatusObs.postValue("请求失败了-${it.message}")
        }
    }
}