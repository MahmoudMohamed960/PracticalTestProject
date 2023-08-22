package com.example.practicaltestproject.Home.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicaltestproject.Home.data.remote.model.FeaturedItem
import com.example.practicaltestproject.R

class FeaturedItemAdapter(val featuredItems: List<FeaturedItem>, val context: Context) :
    RecyclerView.Adapter<FeaturedItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = featuredItems[position]
        holder.itemName.text = item.item_name
        holder.priceCurrencyText.text = item.price
        Glide.with(context).load(item.pic).into(holder.itemPic)
        if (item.same_day_delivery)
            holder.sameDayDelivery.visibility = View.VISIBLE
        else
            holder.sameDayDelivery.visibility = View.GONE


    }

    override fun getItemCount(): Int {
        return featuredItems.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemPic: ImageView = itemView.findViewById(R.id.product_pic)
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val priceCurrencyText: TextView = itemView.findViewById(R.id.price_currency_text)
        val sameDayDelivery: TextView = itemView.findViewById(R.id.same_day_delivery)
    }
}

