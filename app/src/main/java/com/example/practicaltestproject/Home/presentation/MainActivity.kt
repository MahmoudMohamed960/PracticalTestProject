package com.example.practicaltestproject.Home.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaltestproject.Home.data.remote.model.Data
import com.example.practicaltestproject.Home.data.remote.model.FeaturedItem
import com.example.practicaltestproject.R
import com.example.practicaltestproject.utils.MarginItemDecoration
import com.example.practicaltestproject.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: ProductsViewModel by viewModels()
    var keepSplash = true
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen =installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        splashScreen.setKeepOnScreenCondition{
            keepSplash
        }
        lifecycleScope.launch {
            delay(3000)
            keepSplash =false
            getProducts()
        }



    }

    private fun getProducts() {
        viewModel.response.observe(this, Observer { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    response.data?.featuredItem?.let { featureList ->
                        setHorizontalProducts(featureList)
                    }

                    response.data?.data?.let { data ->
                        setVerticalProducts(data)
                    }

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

        })    }


    private fun setVerticalProducts(data: List<Data>) {
        val verticalProductsList: RecyclerView = findViewById(R.id.vertical_products_list)
        val gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        verticalProductsList.layoutManager = gridLayoutManager
        val dataAdapter = DataItemsAdapter(data, this)
        verticalProductsList.adapter = dataAdapter
        verticalProductsList.addItemDecoration(
            MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin))
        )
    }

    private fun setHorizontalProducts(featuredItems: List<FeaturedItem>) {
        val horizontalProductsList: RecyclerView = findViewById(R.id.horizontal_products_list)
        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        horizontalProductsList.layoutManager = layoutManager

        val featuredItemAdapter = FeaturedItemAdapter(featuredItems, this)
        horizontalProductsList.adapter = featuredItemAdapter
    }
}