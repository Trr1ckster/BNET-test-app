package com.trr1ckster.bnet_test_app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.trr1ckster.bnet_test_app.data.model.ApiModelItem
import com.trr1ckster.bnet_test_app.databinding.ItemProductBinding
import com.trr1ckster.bnet_test_app.utils.Utils.Companion.BASE_URL

class ProductsAdapter(private val clickListener: OnItemClickListener) :
    ListAdapter<ApiModelItem, ProductsAdapter.ViewHolder>(ProductsDiffUtil) {

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ApiModelItem) {
            binding.apply {
                title.text = model.name
                description.text = model.description
                imageProduct.load(BASE_URL + model.image)
                root.setOnClickListener { clickListener.onItemClick(model) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val products = getItem(position)
        holder.bind(products)
    }

    private object ProductsDiffUtil : DiffUtil.ItemCallback<ApiModelItem>() {
        override fun areItemsTheSame(oldItem: ApiModelItem, newItem: ApiModelItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ApiModelItem, newItem: ApiModelItem): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClick(model: ApiModelItem)
    }
}