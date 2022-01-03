package com.techand.daggerapp.data.repository

import com.techand.daggerapp.data.network.ApiService
import javax.inject.Inject


class ImageRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getImages() = apiService.getImages()
}