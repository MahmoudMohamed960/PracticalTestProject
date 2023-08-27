package com.example.practicaltestproject.home.data.remote.model

import retrofit2.http.GET

interface ProductsService {
    @GET("products")
    suspend fun getProducts(): ProductModel
}