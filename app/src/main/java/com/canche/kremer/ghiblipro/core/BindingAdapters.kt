package com.canche.kremer.ghiblipro.core

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object BindingAdapters {

    @BindingAdapter("app:loadImage")
    @JvmStatic fun loadImage(view: ImageView, url: String){
        Glide.with(view.context).load(url).centerCrop().
        transition(DrawableTransitionOptions.withCrossFade(300)).into(view)
    }

    @BindingAdapter("app:setRating")
    @JvmStatic fun setRating(view: RatingBar, rating: Int){
        view.rating = rating.toRatingStars
    }

}