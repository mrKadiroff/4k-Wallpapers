package com.example.a4kwallpapers.retrofit


import com.example.a4kwallpapers.models.Photos
import com.example.a4kwallpapers.models2.Rasmlar
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("api")
    fun getListPhotos(
        @Query("key") key: String,
        @Query("q") q:String,
        @Query("image_type") image_type:String,
        @Query("pretty") pretty:Boolean,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<Rasmlar>


    @GET("/api/")
    fun getImageResults(
        @Query("key") key: String?,
        @Query("q") query: String?,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<Rasmlar?>?


}