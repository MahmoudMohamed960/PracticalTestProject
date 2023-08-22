package com.example.practicaltestproject.Home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaltestproject.Home.domain.ProductsRepository
import com.example.practicaltestproject.Home.presentation.model.ProductsModel
import com.example.practicaltestproject.utils.Response
import com.example.practicaltestproject.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(val productsRepository: ProductsRepository) :
    ViewModel() {
    private val _response = MutableLiveData<Response<ProductsModel>>()
    val response: LiveData<Response<ProductsModel>> = _response

    init {
        _response.value = Response.loading()
        viewModelScope.launch {
            val result = productsRepository.getProducts()
            if (result.status == Status.SUCCESS) {
                val productModel =
                    ProductsModel(result.data?.result?.featured_items, result.data?.result?.data)
                _response.value = Response.success(productModel)
            }
            else {
                _response.value = result.message?.let { Response.error(it) }
            }
        }
    }
}