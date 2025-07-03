package com.drygin.kinocopy.features.home.data.api

import com.drygin.kinocopy.features.home.data.model.FilmResponse
import retrofit2.http.GET

/**
 * Created by Drygin Nikita on 02.07.2025.
 */
interface FilmApi {
    @GET("films.json")
    suspend fun getFilms(): FilmResponse
}