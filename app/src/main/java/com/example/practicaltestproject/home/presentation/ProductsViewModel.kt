package com.example.practicaltestproject.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaltestproject.home.data.remote.model.ProductModel
import com.example.practicaltestproject.home.data.repository.ProductsRepositoryImp
import com.example.practicaltestproject.home.presentation.model.ProductItemUiState
import com.example.practicaltestproject.home.presentation.model.ProductsUiState
import com.example.practicaltestproject.utils.Response
import com.example.practicaltestproject.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(val productsRepository: ProductsRepositoryImp) :
    ViewModel() {
    private val _productsUiState = MutableLiveData<Response<ProductsUiState>>()
    val productsUiState: LiveData<Response<ProductsUiState>> = _productsUiState
    private var fetchJob: Job? = null

    init {
        getAllProducts()
    }

    fun getAllProducts() {
        fetchJob?.cancel()
        _productsUiState.value = Response.loading()
        fetchJob = viewModelScope.launch {
            val result = productsRepository.getProducts()
            if (result.status == Status.SUCCESS) {
                if (result.data != null) {
                    val productsList = ArrayList<ProductItemUiState>()
                    result.data.map { productModelItem ->
                        productsList.add(
                            ProductItemUiState(
                                productModelItem.title,
                                productModelItem.price.toString(),
                                productModelItem.image
                            )
                        )
                    }
                    _productsUiState.value = Response.success(ProductsUiState(productsList))
                }
            } else {
                _productsUiState.value = result.message?.let { Response.error(it) }
            }
        }
    }
}