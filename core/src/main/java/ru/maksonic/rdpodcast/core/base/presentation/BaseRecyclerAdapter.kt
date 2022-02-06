package ru.maksonic.rdpodcast.core.base.presentation

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

/**
 * @Author: maksonic on 05.02.2022
 */
abstract class BaseRecyclerAdapter<M : Any, WB : ViewBinding, VH : BaseViewHolder<M, WB>>(
    callback: DiffUtil.ItemCallback<M>
) : ListAdapter<M, VH>(callback) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.doBindings((getItem(position)))
        holder.bind(getItem(position))
    }

    override fun submitList(items: List<M>?) {
        super.submitList(items ?: emptyList())
    }
}

abstract class EmptyItemCallback<M> : DiffUtil.ItemCallback<M>()
