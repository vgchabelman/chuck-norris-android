package br.com.chabelman.presentation.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.chabelman.domain.model.JokeBo
import br.com.chabelman.presentation.R
import br.com.chabelman.presentation.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)
        (activity?.applicationContext as HomeComponentProvider).injectHomeViewModel(viewModel)

        observeRandomJoke()
        observeCategoryList()
        binding.homeSearchView.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
        }
    }

    override fun onResume() {
        super.onResume()

        binding.homeJokeCardGroup.isVisible = false
        binding.homeLoading.isVisible = true
        viewModel.getRandomJoke()
    }

    private fun observeRandomJoke() {
        viewModel.randomJokeBo.observe(viewLifecycleOwner) {
            binding.homeRandomJoke.text = it.value
            setupFavoriteButton(it)
            binding.homeJokeCardGroup.isVisible = true
            binding.homeLoading.isVisible = false
        }
    }

    private fun observeCategoryList() {
        viewModel.categoryList.observe(viewLifecycleOwner) {
            val adapter = CategoryAdapter(it) { category ->
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToJokeDetailFragment(category)
                )
            }
            binding.homeCategoryList.adapter = adapter
        }
        viewModel.updateCategoryList()
    }

    private fun setupFavoriteButton(joke: JokeBo) {
        binding.homeFavoriteButton.isSelected = joke.isFavorite
        binding.homeFavoriteButton.setOnClickListener {
            viewModel.saveFavoriteStatus(joke)
            it.isSelected = !it.isSelected
        }
    }
}