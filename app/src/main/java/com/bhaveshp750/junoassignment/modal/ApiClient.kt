package com.bhaveshp750.junoassignment.modal

import retrofit2.http.GET

interface ApiClient {
    @GET("home")
    suspend fun getHome(): HomeDto

    @GET("empty-home")
    suspend fun getEmptyHome(): HomeDto

    companion object {
        const val BASE_URL = "https://demo9414936.mockable.io/"
    }
}