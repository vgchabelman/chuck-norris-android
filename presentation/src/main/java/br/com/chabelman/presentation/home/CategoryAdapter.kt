package br.com.chabelman.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.chabelman.presentation.R
import br.com.chabelman.presentation.databinding.ItemHomeCategoryBinding
import java.util.Locale

class CategoryAdapter(
    private val categoryList: List<String>,
    private val categoryListener: (category: String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_home_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.itemCategoryName.text = categoryList[position].capitalize(Locale.getDefault())
        holder.binding.root.setOnClickListener {
            categoryListener.invoke(categoryList[position])
        }
    }

    override fun getItemCount(): Int = categoryList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemHomeCategoryBinding.bind(view)
    }
}