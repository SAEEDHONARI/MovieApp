package com.example.moviesapp_saeedhonari.ui.fragments.listmovies

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp_saeedhonari.R
import com.example.moviesapp_saeedhonari.adapter.MoviesAdapter
import com.example.moviesapp_saeedhonari.data.model.Movie
import com.example.moviesapp_saeedhonari.databinding.MoviesListFragmentBinding
import com.example.moviesapp_saeedhonari.util.QUERY_PAGE_SIZE
import com.example.moviesapp_saeedhonari.util.SEARCH_TITLE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.movies_list_fragment.*
import java.util.concurrent.Executors


@AndroidEntryPoint
class MoviesListFragment :  Fragment(R.layout.movies_list_fragment), MoviesAdapter.OnItemClickListener {
    private val viewModel: MoviesListViewModel by viewModels()
    var isLoading = false
    var isLastPage = false
    var isScrolling = false
    val moviesAdapter = MoviesAdapter(this)
    var oldListMovies = mutableSetOf<Movie>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = MoviesListFragmentBinding.bind(view)
        binding.apply {
            rcvMovies.apply {
                adapter = moviesAdapter
                setHasFixedSize(true)
                addOnScrollListener(this@MoviesListFragment.scrollListener)
                setLayoutManager(GridLayoutManager(requireContext(), 2))
            }

            btn_fab.setOnClickListener {
                Executors.newSingleThreadExecutor().execute(Runnable {
                    viewModel.CleareCache()
                })
                Toast.makeText(requireContext(),"The Cache was cleared", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.getMoviesList(SEARCH_TITLE).observe(viewLifecycleOwner) {
            when {
                it.status.isLoading() -> {
                    paginationProgressBar.visibility = View.VISIBLE
                    isLoading = false
                }
                it.status.isSuccessful() -> {
                    it.data?.let { MovieResponse ->
                        paginationProgressBar.visibility = View.INVISIBLE
                  //      oldListMovies.addAll(MovieResponse)
                        moviesAdapter.submitList(MovieResponse.toList())
                        val totalPages = 100 / QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.MoviePage == totalPages
                        if (isLastPage)
                            rcvMovies.setPadding(0, 0, 0, 0)
                    }
                }
                it.status.isError() -> {
                    //  if (it.errorMessage != null)
                    //ToastUtil.showCustomToast(this, it.errorMessage.toString())
                }
            }
        }
    }


    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) { //State is scrolling
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val totalVisibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + totalVisibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate =
                isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling

            if (shouldPaginate) {
                viewModel.getMoviesList(SEARCH_TITLE).observe(viewLifecycleOwner) {
                    when {
                        it.status.isLoading() -> {
                            paginationProgressBar.visibility = View.VISIBLE
                            isLoading = false
                        }
                        it.status.isSuccessful() -> {
                            it.data?.let { MovieResponse ->
                                paginationProgressBar.visibility = View.INVISIBLE
                                oldListMovies.addAll(MovieResponse)
                                Log.i("onScrolled", oldListMovies.toString())
                                moviesAdapter.submitList(oldListMovies.toList())
                                val totalPages = 100 / QUERY_PAGE_SIZE + 2
                                isLastPage = viewModel.MoviePage == totalPages
                                if (isLastPage)
                                    rcvMovies.setPadding(0, 0, 0, 0)
                            }
                        }
                        it.status.isError() -> {
                            //  if (it.errorMessage != null)
                            //ToastUtil.showCustomToast(this, it.errorMessage.toString())
                        }
                    }
                }
                isScrolling = false
            }
        }
    }

    override fun onItemClick(movie: Movie) {
      /*  val action = MovieFragmentDirections.actionMovieFragment2ToDescriptionNewsFragment(Movie)
        findNavController().navigate(action)*/
    }
}


