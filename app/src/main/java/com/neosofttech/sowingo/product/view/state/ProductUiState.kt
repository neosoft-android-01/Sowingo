package com.neosofttech.sowingo.product.view.state

import com.neosofttech.sowingo.product.data.model.Hit

data class ProductUiState(
    val isLoading: Boolean = false,
    val errorMessages: String = "",
    val products: List<Hit>? =  null,
    val filterProduct: List<Hit>? =  null,
    val favorites: Set<String> = emptySet(),
    val searchInput: String = "",
    val showFilterPopup: Boolean = false
) {

    fun toUIState() : IProductUiState {
        return if (products.isNullOrEmpty()) {
            IProductUiState.NoProducts(
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput,
                showFilterPopup = showFilterPopup
            )
        } else {
            IProductUiState.Products(
                products = products,
                filteredProducts = filterProduct!!,
                favorites = favorites,
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput,
                showFilterPopup = showFilterPopup
            )
        }
    }

}


