package br.com.chabelman.presentation.jokeadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.chabelman.domain.model.JokeBo
import br.com.chabelman.presentation.R
import br.com.chabelman.presentation.databinding.ItemJokeBinding

class JokeAdapter(
    private val favoriteListener: (joke: JokeBo) -> Unit
) : RecyclerView.Adapter<JokeAdapter.ViewHolder>() {
    private val jokeList: MutableList<JokeBo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_joke, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val joke = jokeList[position]
        holder.binding.itemJokeText.text = joke.value
        holder.binding.itemJokeFavoriteButton.isSelected = joke.isFavorite
        holder.binding.itemJokeFavoriteButton.setOnClickListener {
            favoriteListener.invoke(joke)
            it.isSelected = !it.isSelected
        }
    }

    override fun getItemCount(): Int = jokeList.size

    fun updateJokeList(newJokeList: List<JokeBo>) {
        val diffUtilCallback = JokeDiffUtil(
            oldList = jokeList,
            newList = newJokeList
        )
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        jokeList.clear()
        jokeList.addAll(newJokeList)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemJokeBinding.bind(view)
    }
}