package com.neosofttech.sowingo.product.data

import com.neosofttech.sowingo.product.data.model.ProductsResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductApiService {

    @GET("products")
    suspend fun fetchProducts() : ProductsResponse

    @POST("favorites")
    suspend fun setFavorites()

    @DELETE("favorites")
    suspend fun removeFavorites()
}