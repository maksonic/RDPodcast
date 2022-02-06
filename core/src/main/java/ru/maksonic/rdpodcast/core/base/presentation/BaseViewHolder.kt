package ru.maksonic.rdpodcast.core.base.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * @Author: maksonic on 05.02.2022
 */
abstract class BaseViewHolder<M, WB : ViewBinding> constructor(viewBinding: WB) :
    RecyclerView.ViewHolder(viewBinding.root) {

    private var item: M? = null

    fun doBindings(data: M?) {
        this.item = data
    }

    //  abstract fun bind()
    abstract fun bind(item: M)

    /*  fun getRowItem(): M? {
          return item
      }*/
}