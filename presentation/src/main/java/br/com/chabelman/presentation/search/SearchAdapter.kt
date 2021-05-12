package br.com.chabelman.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.chabelman.domain.model.JokeBo
import br.com.chabelman.presentation.R
import br.com.chabelman.presentation.databinding.ItemSearchJokeBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private val jokeList: MutableList<JokeBo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_search_joke, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.itemSearchJoke.text = jokeList[position].value
    }

    override fun getItemCount(): Int = jokeList.size

    fun updateJokeList(newJokeList: List<JokeBo>) {
        val diffUtilCallback = SearchJokeDiffUtil(
            oldList = jokeList,
            newList = newJokeList
        )
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        jokeList.clear()
        jokeList.addAll(newJokeList)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemSearchJokeBinding.bind(view)
    }
}