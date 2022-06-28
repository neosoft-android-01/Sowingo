package com.neosofttech.sowingo.product.domain

import com.neosofttech.sowingo.product.data.model.Hit
import kotlinx.coroutines.flow.StateFlow

interface ProductRepository {

   val products: StateFlow<List<Hit>?>

   suspend fun fetchProducts()

   suspend fun toggleFavorites(productId: String, isFavorites: Boolean)

}