package com.neosofttech.sowingo.product.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.neosofttech.sowingo.product.data.model.Hit
import com.neosofttech.sowingo.product.view.ProductsViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.neosofttech.sowingo.R
import com.neosofttech.sowingo.product.view.components.*
import com.neosofttech.sowingo.product.view.state.IProductUiState
import com.neosofttech.sowingo.ui.theme.*

@Composable
fun ProductListingScreen(
    productsViewModel: ProductsViewModel = viewModel()
) {
    val uiState by productsViewModel.uiState.collectAsState()

    Column {

        SearchBar(
            uiState.searchInput,
            onSearchInputChanged = { productsViewModel.onSearchTextChanged(it) },
        )

        FilterOptions(
            shouldShow = uiState.showFilterPopup,
            onShowFilterOption = { productsViewModel.showFilterPopup(it) },
            onOptionClick = { productsViewModel.onFilterOptionClick(it) })

        Spacer(modifier = Modifier.height(dp_8))

        when (uiState) {
            is IProductUiState.Products -> {
                ProductList(
                    uiState = (uiState as IProductUiState.Products),
                    onRefreshProducts = { productsViewModel.refresh() },
                    toggleFav = { productsViewModel.toggleFavorites(it) })
            }
            is IProductUiState.NoProducts -> EmptyContentView(stringResource(id = R.string.no_products))
        }
    }
}

@Composable
private fun ProductList(
    uiState: IProductUiState.Products,
    onRefreshProducts: () -> Unit,
    toggleFav: (id: String) -> Unit
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(uiState.isLoading),
        onRefresh = onRefreshProducts
    ) {
        LazyColumn {
            items(items = uiState.filteredProducts) { item ->
                ListDivider()
                ProductItem(product = item, uiState.favorites.contains(item.id), toggleFav)
            }
        }
    }
}

@Composable
private fun ProductItem(
    product: Hit,
    isFavourite: Boolean,
    onToggleFavorites: (id: String) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .padding(dp_16, dp_16, dp_16, dp_8)
            .fillMaxWidth()
    ) {
        val (box, text, price_1, price_2) = createRefs()
        Box(modifier = Modifier
            .padding(dp_8, dp_8, dp_0, dp_0)
            .constrainAs(box) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }) {
            ImageLoader(url = product.mainImage)
            FavoriteToggle(
                productId = product.id,
                isFavourite = isFavourite,
                onToggleFavorites = onToggleFavorites
            )
        }

        Text(
            text = product.name ?: "",
            maxLines = 5,
            overflow = TextOverflow.Ellipsis,
            style = description,
            modifier = Modifier
                .padding(dp_12, dp_0, dp_0, dp_0)
                .constrainAs(text) {
                    top.linkTo(parent.top)
                    start.linkTo(box.end)
                    end.linkTo(parent.end)
                    bottom.linkTo(price_1.top)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = "$${product.vendorInventory.first().listPrice}",
            style = price1,
            modifier = Modifier
                .padding(dp_0, dp_12, dp_0, dp_0)
                .constrainAs(price_1) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(price_2.start)
                },
        )
        Text(
            text = "$${product.vendorInventory.first().price}",
            style = price2,
            modifier = Modifier
                .padding(dp_8, dp_16, dp_0, dp_0)
                .constrainAs(price_2) {
                    bottom.linkTo(price_1.bottom)
                    end.linkTo(parent.end)
                    top.linkTo(price_1.top)
                },
            textDecoration = TextDecoration.LineThrough,
        )
    }
}