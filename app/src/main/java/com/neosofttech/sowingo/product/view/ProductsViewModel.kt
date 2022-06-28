package com.neosofttech.sowingo.product.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neosofttech.sowingo.global.DEFAULT
import com.neosofttech.sowingo.global.FAVORITE
import com.neosofttech.sowingo.product.domain.usecase.GetAllProductsUseCase
import com.neosofttech.sowingo.product.domain.usecase.SetFavoritesUseCase
import com.neosofttech.sowingo.product.view.state.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val setFavoritesUseCase: SetFavoritesUseCase
): ViewModel() {

    private val favorites = MutableStateFlow<Set<String>>(setOf())

    private val viewModelState = MutableStateFlow(ProductUiState(isLoading = true))

    val uiState = viewModelState
        .map { it.toUIState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUIState()
        )

    init {
        loadProducts()

        viewModelScope.launch {
            favorites.collect { favorites ->
                viewModelState.update { it.copy(favorites = favorites) }
            }
        }
    }

    fun refresh() {
        loadProducts()
    }

    private fun loadProducts() {

        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            getAllProductsUseCase.invoke().collect {
                viewModelState.update { uiState ->
                    uiState.copy(products = it, filterProduct = it, isLoading = false)
                }
            }
        }
    }

    fun toggleFavorites(id: String) {
        viewModelScope.launch {
            val set = favorites.value.toMutableSet()
            val isFav = !set.add(id)
            if (isFav) {
                set.remove(id)
            }
            setFavoritesUseCase.invoke(id, !isFav)
            favorites.value = set.toSet()
        }
    }

    fun onSearchTextChanged(searchInput: String) {
        viewModelState.update {
            val processedList = if(searchInput.isNullOrEmpty()) {
                it.products
            } else {
                it.products?.filter { product->
                    product.name.contains(searchInput, true)
                }
            }
            it.copy(
                searchInput = searchInput,
                filterProduct = processedList
            )
        }
    }

    fun showFilterPopup(shouldShow: Boolean) {
        viewModelState.update { uiState ->
            uiState.copy(showFilterPopup = shouldShow)
        }
    }

    fun onFilterOptionClick(option: String) {
        viewModelState.update {
            val processedList = when(option) {
                FAVORITE -> {
                    it.products?.filter { product ->
                        it.favorites.contains(product.id)
                    }
                }
                DEFAULT -> {
                    it.products?.filter { product ->
                        !it.favorites.contains(product.id)
                    }
                }
                else -> {
                    it.products
                }
            }
            Log.e("onFilterOptionClick", "-- ${processedList?.size}")
            it.copy(showFilterPopup = false, filterProduct = processedList)
        }
    }

}