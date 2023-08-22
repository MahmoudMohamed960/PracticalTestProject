package com.example.practicaltestproject.Home.data.remote.model

import java.lang.Exception

data class Data(
    val ad_overlay: Boolean,
    val currency: String,
    val distance: String,
    val flower_id: String,
    val is_favorite: Boolean,
    val item_name: String,
    val max_quantity: Int,
    val old_price: String,
    val personalization: Boolean,
    val pic: String,
    val price: String,
    val price_per: Double,
    val product_id: Int,
    val same_day_delivery: Boolean,
    val sell_by: String,
    val sell_by_min: Int,
    val sell_by_unit: String,
    val shop_name: String
)