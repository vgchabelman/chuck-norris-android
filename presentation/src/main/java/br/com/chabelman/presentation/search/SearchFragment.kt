package br.com.chabelman.presentation.search

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
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
    private val adapter = SearchAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity?.applicationContext as SearchComponentProvider).injectSearch(viewModel)
        binding = FragmentSearchBinding.bind(view)

        observeJokeList()
        setupSearchQuery()
        binding.searchToolbar.setupWithNavController(findNavController())
        binding.searchList.adapter = adapter
    }

    override fun onPause() {
        super.onPause()

        hideKeyboard()
    }

    private fun observeJokeList() {
        viewModel.jokeList.observe(viewLifecycleOwner) {
            binding.searchEmpty.isVisible = false
            adapter.updateJokeList(it)
        }
    }

    private fun setupSearchQuery() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            var debounceTimer: CountDownTimer? = null

            override fun onQueryTextSubmit(query: String?): Boolean {
                debounceTimer?.cancel()
                doSearch(query)
                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                debounceTimer?.cancel()
                debounceTimer = object : CountDownTimer(500, 200) {
                    override fun onTick(millisUntilFinished: Long) { //noop
                    }

                    override fun onFinish() {
                        doSearch(newText)
                    }
                }
                debounceTimer?.start()

                return false
            }
        })
    }

    private fun doSearch(query: String?) {
        if (query.isNullOrBlank()) {
            binding.searchEmpty.isVisible = true
            adapter.updateJokeList(emptyList())
            return
        }

        viewModel.searchJokes(query)
    }
}