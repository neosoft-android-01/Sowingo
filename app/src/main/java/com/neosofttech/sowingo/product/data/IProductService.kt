package com.neosofttech.sowingo.product.data

import com.neosofttech.sowingo.product.data.model.Hit

interface IProductService {
    suspend fun fetchAllProducts(): List<Hit>?
    suspend fun setFavorites(productId: String, isFavorite: Boolean): Boolean
}