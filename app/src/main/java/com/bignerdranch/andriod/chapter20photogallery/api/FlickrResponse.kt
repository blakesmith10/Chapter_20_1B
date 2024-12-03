package com.bignerdranch.andriod.chapter20photogallery.api

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class FlickrResponse (

    val photos:PhotoResponse
)