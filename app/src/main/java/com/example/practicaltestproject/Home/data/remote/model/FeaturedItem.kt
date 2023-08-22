package com.example.practicaltestproject.Home.data.remote.model

data class FeaturedItem(
    val currency: String,
    val distance: String,
    val flower_id: String,
    val item_name: String,
    val max_quantity: Int,
    val old_price: String,
    val pic: String,
    val price: String,
    val price_per: Double,
    val product_id: Int,
    val record_id: String,
    val same_day_delivery: Boolean,
    val sell_by: String,
    val sell_by_min: Int,
    val sell_by_unit: String,
    val shop_name: String
)