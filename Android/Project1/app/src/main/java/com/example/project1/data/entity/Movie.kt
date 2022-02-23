package com.example.project1.data.entity

import com.google.gson.annotations.SerializedName


data class MovieList(
    @SerializedName("results")
    val movies: List<Movie>
)

data class Movie(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("release_date")
    val releaseDate: String
)


