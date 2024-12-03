package com.bignerdranch.andriod.chapter20photogallery

import com.bignerdranch.andriod.chapter20photogallery.api.FlickrApi
import com.bignerdranch.andriod.chapter20photogallery.api.GalleryItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

class PhotoRepository {

    private val flickrApi:FlickrApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.flickr.com/")
           // .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())

            .build()

        flickrApi = retrofit.create()
    }
   // suspend fun fetchContents() = flickrApi.fetchContents()

   // suspend fun fetchPhotos() = flickrApi.fetchPhotos()
    suspend fun fetchPhotos(): List<GalleryItem> = flickrApi.fetchPhotos().photos.galleryItems


}

