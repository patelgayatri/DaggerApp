package com.techand.daggerapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techand.daggerapp.data.models.ImageResponse
import com.techand.daggerapp.data.network.Resource
import com.techand.daggerapp.data.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(private val repository: ImageRepository) : ViewModel() {

    private val _imageData: MutableLiveData<Resource<ImageResponse>> = MutableLiveData()
    val imageLiveData: LiveData<Resource<ImageResponse>> = _imageData

    init {
        getImages()
    }

    private fun getImages() = viewModelScope.launch {
        _imageData.value = Resource.Loading
        _imageData.value = repository.getImages()
    }

}