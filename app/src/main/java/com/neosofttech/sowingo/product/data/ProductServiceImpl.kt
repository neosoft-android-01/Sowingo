package com.neosofttech.sowingo.product.data

import retrofit2.Retrofit
import javax.inject.Inject

class ProductServiceImpl @Inject constructor(
    retrofit: Retrofit
) : IProductService {

    private val productApiService by lazy { retrofit.create(ProductApiService::class.java) }

    override suspend fun fetchAllProducts() = kotlin.runCatching {
        val response = productApiService.fetchProducts()
        return@runCatching response.hits
    }.onFailure {
        it.printStackTrace()
    }.getOrNull()

    override suspend fun setFavorites(productId: String, isFavorite: Boolean)= kotlin.runCatching {
        if (isFavorite) {
            productApiService.setFavorites()
        } else {
            productApiService.removeFavorites()
        }
        return@runCatching true
    }.onFailure {
        it.printStackTrace()
    }.getOrElse {
        return@getOrElse false
    }
}