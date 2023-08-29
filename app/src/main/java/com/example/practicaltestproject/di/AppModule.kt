package com.example.practicaltestproject.di

import android.app.Application
import com.example.practicaltestproject.home.data.remote.ProductsService
import com.example.practicaltestproject.home.data.repository.ProductsRepositoryImp
import com.example.practicaltestproject.config.remote.RetrofitFactory
import com.example.practicaltestproject.home.domain.repository.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitFactory(application: Application) = RetrofitFactory(application)

    @Singleton
    @Provides
    fun provideProductsService(retrofit: RetrofitFactory) =
        retrofit.createService(ProductsService::class.java)

    @Singleton
    @Provides
    fun provideProductsRepository(productsService: ProductsService):ProductsRepository =
        ProductsRepositoryImp(productsService)
}