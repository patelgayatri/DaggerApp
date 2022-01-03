package com.techand.daggerapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techand.daggerapp.data.models.ImageResponse
import com.techand.daggerapp.data.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(val repository: ImageRepository) : ViewModel() {

    private val _imageData: MutableLiveData<ImageResponse> = MutableLiveData()
    val imageLiveData: LiveData<ImageResponse> = _imageData

    init {
        getImages()
    }

    private fun getImages() = viewModelScope.launch {
        repository.getImages().let {
            if (it.isSuccessful) {
                _imageData.postValue(it.body())
            } else {
                println("=========${it.code()}")
            }
        }
    }

}