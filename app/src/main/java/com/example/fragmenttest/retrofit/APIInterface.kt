package com.example.fragmenttest.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("/search/shows")
    fun getShowData(@Query("q")text:String): Call<TvShows>
}