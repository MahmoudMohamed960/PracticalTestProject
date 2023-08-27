package com.example.practicaltestproject.home.domain.repository

import com.example.practicaltestproject.home.data.remote.model.ProductModel
import com.example.practicaltestproject.utils.Response

interface ProductsRepository {
    suspend fun getProducts():Response<ProductModel>
}