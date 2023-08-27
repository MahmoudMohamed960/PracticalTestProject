package com.example.practicaltestproject.home.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicaltestproject.R
import com.example.practicaltestproject.databinding.VerticalProductItemBinding
import com.example.practicaltestproject.home.data.remote.model.ProductModel

class ProductsAdapter(
    val productsList: ProductModel,
    val context: Context
) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = VerticalProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = productsList[position]
        with(holder)
        {
           binding.itemName.text = item.title
            binding.priceCurrencyText.text = item.price.toString()
            Glide.with(context).load(item.image).into(binding.productPic)
        }

    }

    override fun getItemCount(): Int {
        return productsList.size
    }


    inner class ViewHolder(val binding: VerticalProductItemBinding) : RecyclerView.ViewHolder(binding.root)
}