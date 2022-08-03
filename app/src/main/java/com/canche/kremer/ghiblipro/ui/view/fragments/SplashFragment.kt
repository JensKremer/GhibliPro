package com.canche.kremer.ghiblipro.ui.view.fragments

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.canche.kremer.ghiblipro.R
import com.canche.kremer.ghiblipro.core.extensions.makeGone
import com.canche.kremer.ghiblipro.core.extensions.makeVisible
import com.canche.kremer.ghiblipro.databinding.FragmentSplashBinding
import com.canche.kremer.ghiblipro.ui.UiState
import com.canche.kremer.ghiblipro.ui.states.SplashState
import com.canche.kremer.ghiblipro.ui.viewmodel.GhibliViewModel
import com.canche.kremer.ghiblipro.ui.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel : SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUiState()
    }


    private fun observeUiState(){
        viewModel.uiState.observe(viewLifecycleOwner, Observer(::updateUI))
    }

    private fun updateUI(state: SplashState){
        when(state){
            is SplashState.Downloading -> isLoading()
            is SplashState.SuccessDownloading -> NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment_to_homeFragment)
            is SplashState.ErrorDownloading -> isLoading(false)
        }
    }

    private fun isLoading(loading: Boolean = true){
        if(loading) binding.splashProgressBar.makeVisible()
        else binding.splashProgressBar.makeGone()
    }

}