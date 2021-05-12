package br.com.chabelman.presentation.jokeadapter

import androidx.recyclerview.widget.DiffUtil
import br.com.chabelman.domain.model.JokeBo

class JokeDiffUtil(
    private val oldList: List<JokeBo>,
    private val newList: List<JokeBo>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].value == newList[newItemPosition].value
    }
}