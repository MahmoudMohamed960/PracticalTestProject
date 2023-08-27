package com.example.practicaltestproject.home.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaltestproject.R
import com.example.practicaltestproject.databinding.ActivityMainBinding
import com.example.practicaltestproject.home.data.remote.model.ProductModel
import com.example.practicaltestproject.utils.MarginItemDecoration
import com.example.practicaltestproject.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ProductsViewModel by viewModels()
    var keepSplash = true
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashScreen.setKeepOnScreenCondition {
            keepSplash
        }
        lifecycleScope.launch {
            delay(3000)
            keepSplash = false
            getProducts()
        }


    }

    private fun getProducts() {
        viewModel.response.observe(this, Observer { response ->
            when (response.status) {

                Status.SUCCESS -> {
                    response.data?.let { setProductsList(it) }
                }

                Status.LOADING ->
                    Toast.makeText(this, "Loading data ...", Toast.LENGTH_LONG)
                        .show()

                Status.ERROR ->
                    Toast.makeText(this, response.message, Toast.LENGTH_LONG)
                        .show()

                else ->
                    Toast.makeText(this, "empty list", Toast.LENGTH_LONG)
                        .show()


            }

        })
    }


    private fun setProductsList(products: ProductModel) {
        val verticalProductsList =binding.verticalProductsList
        val gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        verticalProductsList.layoutManager = gridLayoutManager
        val dataAdapter = ProductsAdapter(products, this)
        verticalProductsList.adapter = dataAdapter
        verticalProductsList.addItemDecoration(
            MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin))
        )
    }

}