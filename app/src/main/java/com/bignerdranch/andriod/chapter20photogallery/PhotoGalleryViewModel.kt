package com.bignerdranch.andriod.chapter20photogallery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.andriod.chapter20photogallery.api.GalleryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.random.Random

private const val TAG = "PhotoGalleryViewModel"

class PhotoGalleryViewModel :ViewModel() {

    private val photoRepository = PhotoRepository()

    private val _galleryItems: MutableStateFlow<List<GalleryItem>> =
        MutableStateFlow(emptyList())
    val galleryItem: StateFlow<List<GalleryItem>>
        get() = _galleryItems.asStateFlow()

    init {
        viewModelScope.launch {
            try { // added date url here
                val startDate = LocalDate.of(2022, 1, 1) // Set your start date
                val endDate = LocalDate.now() // Set your end date
                val randomDate = randomDate(startDate, endDate).toString() // Generate random date
                val items = photoRepository.fetchPhotos(randomDate)
                Log.d(TAG, "Items Received : $items")
                _galleryItems.value = items
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch gallery items", ex)
            }
        }
    }

    // added random date function here
    private fun randomDate(start: LocalDate, end: LocalDate): LocalDate {
        val daysBetween = ChronoUnit.DAYS.between(start, end)
        val randomDays = Random.nextLong(daysBetween + 1)
        return start.plusDays(randomDays)
    }
}