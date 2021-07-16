package com.example.moviesapp_saeedhonari.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapp_saeedhonari.data.model.Movie
import com.example.moviesapp_saeedhonari.databinding.ItemMoviePreviewBinding

class MoviesAdapter(private val listener: OnItemClickListener): ListAdapter<Movie, MoviesAdapter.MovieViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMoviePreviewBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class MovieViewHolder(private val binding: ItemMoviePreviewBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        val Movie = getItem(position)
                        listener.onItemClick(Movie)
                    }
                }
            }
        }

        fun bind(movie: Movie){
            binding.apply {
                Glide.with(itemView)
                    .load(movie.poster)
                    .apply(RequestOptions().override(300, 200))
                    .into(ivMoviePoster)
                tvMovieYear.text = movie.year
                tvMovieTitle.text = movie.title
            }

        }
    }

    interface OnItemClickListener{
        fun onItemClick(Movie: Movie)
    }


    class DiffCallback : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.imdbID == newItem.imdbID
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
}