package com.example.practicaltestproject.Home.presentation.model

import com.example.practicaltestproject.Home.data.remote.model.Data
import com.example.practicaltestproject.Home.data.remote.model.FeaturedItem

data class ProductsModel(
   val featuredItem: List<FeaturedItem>?=null,
   val data: List<Data>?=null
)