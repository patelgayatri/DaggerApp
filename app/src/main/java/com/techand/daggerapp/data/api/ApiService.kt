package com.techand.daggerapp.data.api

import com.techand.daggerapp.data.models.ImageResponse
import com.techand.daggerapp.util.Constants.END_POINT
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(END_POINT)
    suspend fun getImages(): Response<ImageResponse>
}