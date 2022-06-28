package com.neosofttech.sowingo.product.view.state

import com.neosofttech.sowingo.product.data.model.Hit

sealed interface IProductUiState {
    val isLoading: Boolean
    val errorMessages: String
    val searchInput: String
    val showFilterPopup: Boolean

    data class NoProducts(
        override val showFilterPopup: Boolean,
        override val isLoading: Boolean,
        override val errorMessages: String,
        override val searchInput: String
    ) : IProductUiState

    data class Products(
        val products: List<Hit>,
        val filteredProducts: List<Hit>,
        val favorites: Set<String> = emptySet(),
        override val showFilterPopup: Boolean,
        override val isLoading: Boolean,
        override val errorMessages: String,
        override val searchInput: String
    ) : IProductUiState
}