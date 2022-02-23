package com.example.project1.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {
    lateinit var binding: FragmentMovieBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = MovieViewModel()
        viewModel.getMovies()
        viewModel._movies.observe(viewLifecycleOwner, {
            if (it.isSuccessful) {
                val movies = it.body()?.movies!!
                val adapter = MovieAdapter(movies)
                recyclerView = binding.rvMovieList
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.setHasFixedSize(true)
            }
        })


    }
}