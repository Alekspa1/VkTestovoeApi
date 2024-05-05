package com.drag0n.vktestovoeapi.view.adapters

import androidx.recyclerview.widget.DiffUtil
import com.drag0n.vktestovoeapi.data.Product

class DiffCallback: DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }


}