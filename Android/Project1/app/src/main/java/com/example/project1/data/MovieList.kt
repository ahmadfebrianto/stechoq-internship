package com.example.project1.data

import com.example.project1.data.entity.Movie


object MovieList {
    fun getMovies(): ArrayList<Movie> {
        return arrayListOf(
            Movie(
                1,
                "Movie 1",
                "Movie 1 description"
            ),
            Movie(
                1,
                "Movie 2",
                "Movie 2 description"
            ),
            Movie(
                1,
                "Movie 3",
                "Movie 3 description"
            ),
            Movie(
                1,
                "Movie 4",
                "Movie 4 description"
            ),
        )
    }
}