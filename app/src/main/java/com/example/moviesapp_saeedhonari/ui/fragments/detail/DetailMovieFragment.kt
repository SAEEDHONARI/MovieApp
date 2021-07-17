package com.example.moviesapp_saeedhonari.ui.fragments.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviesapp_saeedhonari.R
import com.example.moviesapp_saeedhonari.adapter.GenreAdapter
import com.example.moviesapp_saeedhonari.adapter.RatingAdapter
import com.example.moviesapp_saeedhonari.data.model.Ratings
import com.example.moviesapp_saeedhonari.databinding.DetailMovieFragmentBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.collapsing_toolbar.*
import kotlinx.android.synthetic.main.detail_movie_fragment.*

@AndroidEntryPoint
class DetailMovieFragment : Fragment(R.layout.detail_movie_fragment) {
    private val args by navArgs<DetailMovieFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = DetailMovieFragmentBinding.bind(view)
        SetUi(binding)

    }
    private val ratings= arrayListOf<Ratings>()
    private val genres= arrayListOf<String>()

    private fun SetUi(binding: DetailMovieFragmentBinding) {
        binding.apply {
            val movie = args.description
            Glide.with(requireContext())
                .load(movie.poster)
                .into(iv_poster)

            tv_title.setText(movie.title)
            tv_omdb_rate.setText(movie.imdbRating+"/10 IMDB")
            tv_released.setText(movie.released)
            tv_director.setText( movie.director)
            tv_writer.setText(movie.writer)
            tv_rated.setText(movie.rated)
            tv_plot.setText(movie.plot)
             genres.add(movie.genre!!)
             ratings.addAll(movie.ratings!!)
            initGenresRecyclerView()
             initRatingRecyclerView()
            updateUi(false)

        }
    }

    private fun initGenresRecyclerView() {
        rv_genre.setHasFixedSize(true)
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.setFlexWrap(FlexWrap.WRAP)
        layoutManager.setFlexDirection(FlexDirection.ROW)
        layoutManager.setJustifyContent(JustifyContent.FLEX_START)
        rv_genre.layoutManager = layoutManager
        val adapter = GenreAdapter(genres)
        rv_genre.adapter = adapter
    }

    fun initRatingRecyclerView() {
        rv_ratings.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL)
        rv_ratings.layoutManager = linearLayoutManager
        val adapter = RatingAdapter(ratings)
        rv_ratings.adapter = adapter
    }

    fun onFailed(message: kotlin.String?) {
        if (activity != null) {
          //  Snack.Error(activity, message)
        }
    }

    fun updateUi(isLoading: Boolean) {
        pg_movie.visibility = if (isLoading) View.VISIBLE else View.GONE
        sv_movie.visibility = if (isLoading) View.GONE else View.VISIBLE
    }
}