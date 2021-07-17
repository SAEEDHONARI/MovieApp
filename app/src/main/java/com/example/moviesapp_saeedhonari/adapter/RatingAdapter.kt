package com.example.moviesapp_saeedhonari.adapter

import android.media.Rating
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp_saeedhonari.R
import com.example.moviesapp_saeedhonari.adapter.RatingAdapter.RatingViewHolder
import com.example.moviesapp_saeedhonari.data.model.Ratings
import kotlinx.android.synthetic.main.genre_item.view.*
import kotlinx.android.synthetic.main.rating_item.view.*
import java.util.*

class RatingAdapter(private val ratings: ArrayList<Ratings>?) :
    RecyclerView.Adapter<RatingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        return RatingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rating_item, parent, false)
        )
    }


    override fun onBindViewHolder(holder: RatingAdapter.RatingViewHolder, position: Int) {
        val currentItem = ratings!!.get(position)
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return ratings?.size ?: 0
    }

    inner class RatingViewHolder(private val binding: View): RecyclerView.ViewHolder(binding.rootView) {
        fun bind(rate: Ratings){
            binding.apply {
                val rating: Ratings = rate
                tv_source.setText(rating.source)
                tv_rating.setText(rating.value)
            }

        }

    }

}