package br.com.chabelman.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import br.com.chabelman.presentation.R
import br.com.chabelman.presentation.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment: Fragment(R.layout.fragment_home) {
    lateinit var binding: FragmentHomeBinding
    private val viewModel:  HomeViewModel by viewModels()
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)
        (activity?.applicationContext as HomeComponentProvider).injectHomeViewModel(viewModel)

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