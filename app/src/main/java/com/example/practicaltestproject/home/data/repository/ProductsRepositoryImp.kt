package com.example.practicaltestproject.home.data.repository

import com.example.practicaltestproject.home.data.remote.model.ProductsService
import com.example.practicaltestproject.home.domain.repository.ProductsRepository
import com.example.practicaltestproject.config.remote.NoConnectivityException
import com.example.practicaltestproject.utils.Response
import java.lang.Exception
import java.net.SocketTimeoutException
import javax.inject.Inject

class ProductsRepositoryImp @Inject constructor(val productsService: ProductsService) :ProductsRepository{
    override suspend fun getProducts() = try {
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