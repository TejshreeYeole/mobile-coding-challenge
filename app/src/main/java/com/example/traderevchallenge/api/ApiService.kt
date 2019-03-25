package com.example.traderevchallenge.api

import com.example.traderevchallenge.model.CuratedPhotoModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/photos/curated")
    fun getCuratedPhotos(@Query("page")  page : Int ,
                         @Query("per_page")  per_page : Int ,
                         @Query("order_by")  order_by : String,
                         @Query("client_id") client_id : String) : Deferred<ArrayList<CuratedPhotoModel>>
}
