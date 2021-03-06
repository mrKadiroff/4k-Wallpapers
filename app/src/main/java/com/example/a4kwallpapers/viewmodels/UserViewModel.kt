package com.example.a4kwallpapers.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.a4kwallpapers.retrofit.RetrofitClient

class UserViewModel : ViewModel() {

   var word:String = ""
    val liveData = Pager(PagingConfig(pageSize = 2)){

            UserDataSource(RetrofitClient.apiService,word)
        }.liveData.cachedIn(viewModelScope)



}