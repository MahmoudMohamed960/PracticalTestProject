package com.example.practicaltestproject.Home.data.remote.model

import com.example.practicaltestproject.Home.data.remote.model.Data
import com.example.practicaltestproject.Home.data.remote.model.FeaturedItem

data class Result(
    val can_load: Boolean,
    val data: List<Data>,
    val featured_items: List<FeaturedItem>,
    val pickup: Boolean,
    val preparation_time: String,
    val same_day_delivery: Boolean,
    val shop_banner: String,
    val shop_logo: String,
    val shop_name: String,
    val shop_rating: Int,
    val shop_rating_count: String,
    val shop_rating_dec: Int,
    val shop_reviews_count: Int,
    val status: String
)