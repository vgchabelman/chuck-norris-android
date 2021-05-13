package br.com.chabelman.presentation.favorites

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import br.com.chabelman.presentation.R
import br.com.chabelman.presentation.databinding.FragmentFavoritesBinding
import br.com.chabelman.presentation.jokeadapter.JokeAdapter

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var binding: FragmentFavoritesBinding

    private val adapter = JokeAdapter {
        viewModel.saveFavoriteStatus(it)
        viewModel.updateFavoritesList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity?.applicationContext as FavoriteComponentProvider).injectFavoritesViewModel(
            viewModel
        )
        binding = FragmentFavoritesBinding.bind(view)

        binding.favoritesList.adapter = adapter

        observeFavoriteJokes()
    }

    override fun onResume() {
        super.onResume()

        viewModel.updateFavoritesList()
    }

    private fun observeFavoriteJokes() {
        viewModel.favoritesList.observe(viewLifecycleOwner) {
            binding.favoriteEmpty.isVisible = it.isEmpty()
            adapter.updateJokeList(it)
        }
    }
}