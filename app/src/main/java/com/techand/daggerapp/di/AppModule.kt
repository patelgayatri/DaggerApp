package com.techand.daggerapp.di

import com.techand.daggerapp.data.network.ApiService
import com.techand.daggerapp.data.network.NetworkConnectionInterCeptor
import com.techand.daggerapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofitInstance(networkConnectionInterCeptor: NetworkConnectionInterCeptor): ApiService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(networkConnectionInterCeptor)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(
                ApiService::
                class.java
            )
    }
}