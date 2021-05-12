package br.com.chabelman.presentation.search

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import br.com.chabelman.presentation.R
import br.com.chabelman.presentation.databinding.FragmentSearchBinding
import br.com.chabelman.presentation.extension.hideKeyboard

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity?.applicationContext as SearchComponentProvider).injectSearch(viewModel)
        binding = FragmentSearchBinding.bind(view)

        observeJokeList()
        setupSearchQuery()
        binding.searchToolbar.setupWithNavController(findNavController())
    }

    private fun observeJokeList() {
        viewModel.jokeList.observe(viewLifecycleOwner) {
            val adapter = SearchAdapter(it)
            binding.searchList.adapter = adapter
        }
    }

    private fun setupSearchQuery() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchJokes(it) }
                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}