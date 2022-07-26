package com.canche.kremer.ghiblipro.ui.view.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.canche.kremer.ghiblipro.databinding.ItemHomeBinding
import com.canche.kremer.ghiblipro.domain.models.Film

class FilmViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemHomeBinding.bind(view)

    fun render(film: Film, onClickListener: (Film) -> Unit){
        binding.titleMovieElement.text = film.title
        binding.yearMovieElement.text = film.releaseDate.toString()
        Glide.with(binding.moviePictureElement.context).load(film.image).centerCrop().
        transition(DrawableTransitionOptions.withCrossFade(300)).into(binding.moviePictureElement)
        itemView.setOnClickListener{onClickListener(film)}
    }

}