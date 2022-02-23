package com.example.project1.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project1.data.entity.Movie
import com.example.project1.data.entity.MovieList
import com.example.project1.service.ApiService
import com.example.project1.service.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel: ViewModel() {
    val _movies : MutableLiveData<Response<MovieList>> = MutableLiveData()

    fun getMovies() {
        viewModelScope.launch {
            val movies = RetrofitInstance.apiService.getMovies()
            _movies.value = movies
        }
    }
}