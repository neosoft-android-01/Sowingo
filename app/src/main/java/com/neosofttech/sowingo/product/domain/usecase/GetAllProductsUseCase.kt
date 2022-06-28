package com.neosofttech.sowingo.product.domain.usecase

import com.neosofttech.sowingo.di.annotations.WorkDispatcher
import com.neosofttech.sowingo.product.domain.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GetAllProductsUseCase @Inject constructor(
    @WorkDispatcher private val workDispatcher: CoroutineDispatcher,
    private val productRepository: ProductRepository
) {

    suspend operator fun invoke() = withContext(workDispatcher) {
        productRepository.fetchProducts()
        productRepository.products
    }
}