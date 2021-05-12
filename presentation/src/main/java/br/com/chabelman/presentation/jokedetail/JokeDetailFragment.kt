package br.com.chabelman.presentation.jokedetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import br.com.chabelman.presentation.R
import br.com.chabelman.presentation.databinding.FragmentJokeDetailBinding
import coil.load
import java.util.Locale
import kotlin.random.Random


class JokeDetailFragment : Fragment(R.layout.fragment_joke_detail) {
    private lateinit var binding: FragmentJokeDetailBinding
    private val args: JokeDetailFragmentArgs by navArgs()
    private val viewModel: JokeDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentJokeDetailBinding.bind(view)
        (activity?.applicationContext as JokeDetailComponentProvider).jokeDetailInject(viewModel)

        observeJoke()
        binding.detailUpdateButton.setOnClickListener {
            updateJoke()
        }

        binding.detailToolbar.setupWithNavController(findNavController())
    }

    override fun onResume() {
        super.onResume()

        updateJoke()
    }

    private fun observeJoke() {
        viewModel.joke.observe(viewLifecycleOwner) { joke ->
            binding.detailFact.text = joke.value
            val randomNumber = Math.abs(Random.nextInt())
            binding.detailToolbar.title = getString(R.string.detailToolbar, randomNumber)
            val categoryName = args.category.capitalize(Locale.getDefault())
            binding.detailCategory.text = getString(R.string.detailFact, categoryName)
            binding.detailIcon.load(joke.icon) {
                placeholder(R.drawable.ic_round_image_48)
            }
            updateLinkUrl(joke.url)
            binding.factGroup.isVisible = true
            binding.detailLoading.isVisible = false
        }
    }

    private fun updateLinkUrl(url: String) {
        binding.detailMoreInfoButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun updateJoke() {
        binding.factGroup.isVisible = false
        binding.detailLoading.isVisible = true
        viewModel.getRandomJoke(args.category)
    }
}