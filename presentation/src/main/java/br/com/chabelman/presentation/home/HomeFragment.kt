package br.com.chabelman.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.chabelman.presentation.R
import br.com.chabelman.presentation.databinding.FragmentHomeBinding

class HomeFragment: Fragment(R.layout.fragment_home) {
    lateinit var binding: FragmentHomeBinding
    private val viewModel:  HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        observeRandomJoke()
    }

    override fun onResume() {
        super.onResume()

        viewModel.getRandomJoke()
    }

    private fun observeRandomJoke() {
        viewModel.randomJokeBo.observe(viewLifecycleOwner) {
            binding.homeRandomJoke.text = it.value
        }
    }
}