package com.example.moviesapp_saeedhonari.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp_saeedhonari.R
import com.example.moviesapp_saeedhonari.adapter.GenreAdapter.GenreViewHolder
import com.example.moviesapp_saeedhonari.data.model.Movie
import kotlinx.android.synthetic.main.genre_item.view.*
import java.util.*

class GenreAdapter(private val genres: ArrayList<String>?) :
    RecyclerView.Adapter<GenreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.genre_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val currentItem = genres!!.get(position)
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return genres?.size ?: 0
    }

    inner class GenreViewHolder(private val binding: View): RecyclerView.ViewHolder(binding.rootView) {
          fun bind(genre: String){
            binding.apply {
                tv_genre.text = genre
            }

        }

    }


}