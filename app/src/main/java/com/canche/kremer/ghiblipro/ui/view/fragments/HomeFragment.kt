package com.canche.kremer.ghiblipro.ui.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.canche.kremer.ghiblipro.R
import com.canche.kremer.ghiblipro.databinding.FragmentHomeBinding
import com.canche.kremer.ghiblipro.databinding.FragmentSplashBinding
import com.canche.kremer.ghiblipro.domain.models.Film
import com.canche.kremer.ghiblipro.ui.view.adapters.RecyclerViewAdapter
import com.canche.kremer.ghiblipro.ui.viewmodel.GhibliViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel : GhibliViewModel by activityViewModels()

    private val adapter = RecyclerViewAdapter() { film -> onFilmClick(film)  }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view).apply {
            homeRecycler.adapter = adapter
        }

        binding.onRefresh = viewModel::onRefresh
        binding.searchFilm = viewModel::searchFilm

        observeFilms()
    }

    private fun observeFilms(){
        viewModel.films.observe(viewLifecycleOwner){
            viewModel.films.value?.let { films -> adapter.updateFilms(films) }
        }
    }

    private fun onFilmClick(film: Film){
        viewModel.setSelectedFilm(film)
        NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_filmFragment)
    }


}