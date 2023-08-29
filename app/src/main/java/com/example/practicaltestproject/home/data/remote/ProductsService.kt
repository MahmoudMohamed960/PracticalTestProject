package com.example.practicaltestproject.home.data.remote

import com.example.practicaltestproject.home.data.remote.model.ProductModel
import retrofit2.http.GET

interface ProductsService {
    @GET("products")
    suspend fun getProducts(): ProductModel
}