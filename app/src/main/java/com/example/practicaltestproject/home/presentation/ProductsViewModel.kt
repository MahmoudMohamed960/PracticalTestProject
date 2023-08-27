package com.example.practicaltestproject.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaltestproject.home.data.remote.model.ProductModel
import com.example.practicaltestproject.home.data.repository.ProductsRepositoryImp
import com.example.practicaltestproject.utils.Response
import com.example.practicaltestproject.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(val productsRepository: ProductsRepositoryImp) :
    ViewModel() {
    private val _response = MutableLiveData<Response<ProductModel>>()
    val response: LiveData<Response<ProductModel>> = _response

    init {
        _response.value = Response.loading()
        viewModelScope.launch {
            val result = productsRepository.getProducts()
            if (result.status == Status.SUCCESS) {
                if (result.data != null) {
                    _response.value = Response.success(result.data)
                }
            } else {
                _response.value = result.message?.let { Response.error(it) }
            }
        }
    }
}