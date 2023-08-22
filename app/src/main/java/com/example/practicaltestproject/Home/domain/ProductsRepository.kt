package com.example.practicaltestproject.Home.domain

import com.example.practicaltestproject.Home.data.remote.ProductsService
import com.example.practicaltestproject.config.remote.NoConnectivityException
import com.example.practicaltestproject.utils.Response
import java.lang.Exception
import java.net.SocketTimeoutException
import javax.inject.Inject

class ProductsRepository @Inject constructor(val productsService: ProductsService) {
    suspend fun getProducts() = try {
        Response.success(productsService.getProducts())
    } catch (exception:Exception) {
        when (exception) {
            is SocketTimeoutException -> {
                Response.error("Time out !!")
            }
            is NoConnectivityException -> {
                Response.error(exception.errorMsg())
            }
            else -> {
                Response.error("Error occured !!")
            }
        }
    }
}