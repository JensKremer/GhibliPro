package com.canche.kremer.ghiblipro.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.canche.kremer.ghiblipro.core.extensions.toHoursAndMinutes
import com.canche.kremer.ghiblipro.databinding.FragmentFilmBinding
import com.canche.kremer.ghiblipro.domain.models.Film
import com.canche.kremer.ghiblipro.ui.viewmodel.FilmViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmFragment : Fragment() {

    private lateinit var binding: FragmentFilmBinding
    private val args: FilmFragmentArgs by navArgs()
    private val viewModel : FilmViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getFilmById(args.filmId)
        binding = FragmentFilmBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFilm()
    }

    private fun observeFilm(){
        viewModel.film.observe(viewLifecycleOwner, Observer(::updateUi))
    }

    private fun updateUi(film: Film){
        binding.film = film
        binding.runningTime = film.runningTime.toHoursAndMinutes
    }

}