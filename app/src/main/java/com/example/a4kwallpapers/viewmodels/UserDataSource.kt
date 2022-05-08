package com.example.a4kwallpapers.viewmodels

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.a4kwallpapers.models2.Hit
import com.example.a4kwallpapers.retrofit.RetrofitService
import java.lang.Exception

class UserDataSource (val apiService: RetrofitService,val photo:String): PagingSource<Int, Hit>() {
    private val TAG = "UserDataSource"
    override fun getRefreshKey(state: PagingState<Int, Hit>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hit> {
        try {
            var pahto:String
            Log.d(TAG, "load: $photo")
            val nextPageNumber = params.key ?: 1
            val usersData = apiService.getListPhotos("27240519-6e85e045b4edde1049de33f01",photo,"photo",true,nextPageNumber,15)
            return LoadResult.Page(usersData.hits,null,nextPageNumber + 1)

        }catch (e: Exception){
            return LoadResult.Error(e)
        }




    }
}