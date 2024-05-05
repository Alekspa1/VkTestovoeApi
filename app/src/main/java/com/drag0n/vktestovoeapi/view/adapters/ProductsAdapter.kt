package com.drag0n.vktestovoeapi.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.drag0n.vktestovoeapi.data.Product
import com.drag0n.vktestovoeapi.databinding.ProductItemBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

class ProductsAdapter(private val listener: Listener): ListAdapter<Product, ProductsAdapter.ViewHolder>(DiffCallback()) {
    class ViewHolder(private val binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Product, listener: Listener){
            with(binding){
                tvTitle.text = item.title
                tvDesc.text = item.description
                Picasso.get().load(item.thumbnail).into(imageView)
                root.setOnClickListener {
                    listener.listener(item)
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    interface Listener{
        fun listener(item: Product)
    }
}
