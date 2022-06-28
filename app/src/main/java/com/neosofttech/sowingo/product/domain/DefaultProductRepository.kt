package com.neosofttech.sowingo.product.domain

import com.neosofttech.sowingo.product.data.IProductService
import com.neosofttech.sowingo.product.data.model.Hit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DefaultProductRepository @Inject constructor(
    private val productService: IProductService
) : ProductRepository {

    private val _products = MutableStateFlow<List<Hit>?>(null)

    override val products: StateFlow<List<Hit>?>
        get() = _products

    override suspend fun fetchProducts() {
        _products.emit(productService.fetchAllProducts())
    }

    override suspend fun toggleFavorites(productId: String, isFavorites: Boolean) {
        productService.setFavorites(productId,isFavorites)
    }
}