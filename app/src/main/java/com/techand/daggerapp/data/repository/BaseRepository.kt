package com.techand.daggerapp.data.repository

import com.techand.daggerapp.data.network.ApiService
import com.techand.daggerapp.data.network.SafeApiCall

abstract class BaseRepository(private val api: ApiService) : SafeApiCall {

}