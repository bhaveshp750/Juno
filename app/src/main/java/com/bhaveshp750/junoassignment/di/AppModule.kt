package com.bhaveshp750.junoassignment.di

import com.bhaveshp750.junoassignment.modal.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApiClient(): ApiClient {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiClient.BASE_URL)
            .build()
            .create(ApiClient::class.java)
    }
}