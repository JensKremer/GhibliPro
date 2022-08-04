package com.canche.kremer.ghiblipro.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.canche.kremer.ghiblipro.R
import com.canche.kremer.ghiblipro.databinding.FragmentHomeBinding
import com.canche.kremer.ghiblipro.databinding.FragmentSplashBinding
import com.canche.kremer.ghiblipro.ui.states.HomeState
import com.canche.kremer.ghiblipro.ui.view.adapters.RecyclerViewAdapter
import com.canche.kremer.ghiblipro.ui.viewmodel.HomeViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel : HomeViewModel by viewModels()

    private val adapter by lazy { RecyclerViewAdapter(onClickListener = viewModel::onFilmSelected) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeRecycler.adapter = adapter
        binding.onRefresh = viewModel::onRefresh
        binding.searchFilm = viewModel::searchFilm
        observeUiState()
    }

    private fun observeUiState(){
        viewModel.uiState.observe(viewLifecycleOwner, Observer(::updateUI))
    }

    private fun toFilmFragment(id: String){
        val action = HomeFragmentDirections.actionHomeFragmentToFilmFragment(id)
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.setWating()
    }

    private fun updateUI(state: HomeState){
        when(state){
            is HomeState.Navigate -> toFilmFragment(state.film.id)
            is HomeState.LoadFilms -> adapter.updateFilms(state.films)
            is HomeState.ErrorDownloading ->{}
            is HomeState.Waiting -> {}
        }
    }
}