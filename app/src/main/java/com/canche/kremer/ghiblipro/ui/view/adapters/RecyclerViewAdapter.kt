package com.canche.kremer.ghiblipro.ui.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canche.kremer.ghiblipro.R
import com.canche.kremer.ghiblipro.domain.models.Film

class RecyclerViewAdapter(
    private val films: MutableList<Film> = mutableListOf(),
    var onClickListener:(Film) -> Unit = {}
) : RecyclerView.Adapter<FilmViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FilmViewHolder(layoutInflater.inflate(R.layout.item_home, parent, false))
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.render(films[position], onClickListener)
    }

    override fun getItemCount(): Int {
        return films.size
    }

    fun updateFilms(films: List<Film>){
        this.films.clear()
        this.films.addAll(films)
        notifyDataSetChanged()
    }


}