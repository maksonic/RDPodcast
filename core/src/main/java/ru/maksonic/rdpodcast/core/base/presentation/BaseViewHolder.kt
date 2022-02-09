package ru.maksonic.rdpodcast.core.base.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlin.properties.Delegates

/**
 * @Author: maksonic on 05.02.2022
 */
abstract class BaseViewHolder<M, WB : ViewBinding> constructor(viewBinding: WB) :
    RecyclerView.ViewHolder(viewBinding.root) {

    private var item: M? = null

    var selectedPosition: Int = 0

    fun doBindings(data: M?) {
        this.item = data
    }

    abstract fun bind()

    fun getRowItem(): M? {
        return item
    }
}