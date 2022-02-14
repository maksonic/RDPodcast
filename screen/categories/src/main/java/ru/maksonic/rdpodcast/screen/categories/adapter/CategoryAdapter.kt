package ru.maksonic.rdpodcast.screen.categories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import ru.maksonic.rdpodcast.core.base.presentation.BaseRecyclerAdapter
import ru.maksonic.rdpodcast.screen.categories.databinding.ItemCategoryBinding
import ru.maksonic.rdpodcast.shared.ui_model.CategoryUi

/**
 * @Author: maksonic on 07.02.2022
 */
class CategoryAdapter constructor(
    private val onCategoryClicked: ((CategoryUi?) -> Unit)? = null,
    private val imageLoader: RequestManager
) : BaseRecyclerAdapter<CategoryUi, ItemCategoryBinding, CategoryViewHolder>(CategoryItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            click = onCategoryClicked,
            imageLoader
        )
}

class CategoryItemDiffUtil : DiffUtil.ItemCallback<CategoryUi>() {
    override fun areItemsTheSame(oldItem: CategoryUi, newItem: CategoryUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CategoryUi, newItem: CategoryUi): Boolean {
        return oldItem == newItem
    }

}
