package com.techand.daggerapp.data.repository

import com.techand.daggerapp.data.network.ApiService
import javax.inject.Inject


class ImageRepository @Inject constructor(private val apiService: ApiService):BaseRepository(apiService)  {

    suspend fun getImages() = safeApiCall {
        apiService.getImages()
    }
}