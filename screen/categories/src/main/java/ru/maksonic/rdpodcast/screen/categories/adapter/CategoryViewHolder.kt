package ru.maksonic.rdpodcast.screen.categories.adapter

import com.bumptech.glide.RequestManager
import ru.maksonic.rdpodcast.core.base.presentation.BaseViewHolder
import ru.maksonic.rdpodcast.core.ui.click
import ru.maksonic.rdpodcast.screen.categories.databinding.ItemCategoryBinding
import ru.maksonic.rdpodcast.shared.ui_model.CategoryUi

/**
 * @Author: maksonic on 07.02.2022
 */
class CategoryViewHolder constructor(
    private val binding: ItemCategoryBinding,
    private val click: ((CategoryUi?) -> Unit)? = null,
    private val imageLoader: RequestManager
) : BaseViewHolder<CategoryUi, ItemCategoryBinding>(binding) {

    init {
        binding.categoryCard.click {
            click?.invoke(getRowItem())
        }
    }

    override fun bind() {
        getRowItem()?.let {

            with(binding) {
                categoryName.text = it.name
                categoryDescription.text = it.description
                imageLoader.load(it.image).into(imgCategory)
            }
        }
    }
}