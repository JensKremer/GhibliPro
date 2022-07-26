package com.canche.kremer.ghiblipro.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.canche.kremer.ghiblipro.R
import com.canche.kremer.ghiblipro.databinding.FragmentSplashBinding
import com.canche.kremer.ghiblipro.ui.viewmodel.GhibliViewModel


class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel : GhibliViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFilms()
    }

    private fun observeFilms(){
        viewModel.films.observe(viewLifecycleOwner){
            NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment_to_homeFragment)
        }
    }

}