package com.techand.daggerapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val desc: String,
    val id: Int,
    val path: String
): Parcelable