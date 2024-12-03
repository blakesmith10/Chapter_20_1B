package com.bignerdranch.andriod.chapter20photogallery.api

import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "84b10f373865f313a1120b2a6aa6a047"

interface FlickrApi {

    /*@GET("/")
    suspend fun fetchContents(): String*/

    @GET("services/rest/")
    suspend fun fetchPhotos(
        @Query("method") method: String = "flickr.interestingness.getList",
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("extras") extras: String = "url_s",
    ): FlickrResponse
}