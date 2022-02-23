package com.example.project1.service

import com.example.project1.data.entity.Movie
import com.example.project1.data.entity.MovieList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("upcoming?api_key=1fcf815abf04f454d44c9c56df60de5d&language=en-US&page=1")
    suspend fun getMovies(): Response<MovieList>
}