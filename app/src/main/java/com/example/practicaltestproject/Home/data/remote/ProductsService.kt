package com.example.practicaltestproject.Home.data.remote

import com.example.practicaltestproject.Home.data.remote.model.ProductsModel
import retrofit2.http.GET

interface ProductsService {
    @GET("testUrlVer1.1")
    suspend fun getProducts(): ProductsModel
}