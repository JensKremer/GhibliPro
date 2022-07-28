package com.canche.kremer.ghiblipro.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.canche.kremer.ghiblipro.R
import com.canche.kremer.ghiblipro.core.toHoursAndMinutes
import com.canche.kremer.ghiblipro.databinding.FragmentFilmBinding
import com.canche.kremer.ghiblipro.databinding.FragmentHomeBinding
import com.canche.kremer.ghiblipro.ui.viewmodel.GhibliViewModel


class FilmFragment : Fragment() {

    private lateinit var binding: FragmentFilmBinding
    private val viewModel : GhibliViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding =  FragmentFilmBinding.bind(view)
        binding.film = viewModel.filmSelect.value
        binding.runningTime = viewModel.filmSelect.value?.runningTime?.toHoursAndMinutes ?: "0"
    }

}