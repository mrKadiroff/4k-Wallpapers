package com.example.a4kwallpapers.viewmodels

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.a4kwallpapers.models2.Hit
import com.example.a4kwallpapers.retrofit.RetrofitService
import java.lang.Exception

class UserDataSource (val apiService: RetrofitService): PagingSource<Int, Hit>() {
    override fun getRefreshKey(state: PagingState<Int, Hit>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hit> {
        try {
            val nextPageNumber = params.key ?: 1
            val usersData = apiService.getListPhotos()
            return LoadResult.Page(usersData.hits,null,nextPageNumber + 1)

        }catch (e: Exception){
            return LoadResult.Error(e)
        }




    }
}