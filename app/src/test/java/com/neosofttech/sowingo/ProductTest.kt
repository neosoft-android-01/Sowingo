package com.neosofttech.sowingo

import com.neosofttech.sowingo.global.FAVORITE
import com.neosofttech.sowingo.product.data.model.Hit
import com.neosofttech.sowingo.product.domain.usecase.GetAllProductsUseCase
import com.neosofttech.sowingo.product.view.ProductsViewModel
import com.neosofttech.sowingo.product.view.state.IProductUiState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class ProductTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `when api return list of Hit show Listing Screen`() {

        coroutinesTestRule.runBlockingTest {
            // given
            val getAllProductsUseCase: GetAllProductsUseCase = mockk(relaxed = true)
            val viewModel = ProductsViewModel(
                getAllProductsUseCase,
                mockk(relaxed = true)
            )

            val products: MutableList<Hit> = mutableListOf()
            products.add(mockk(relaxed = true))
            products.add(mockk(relaxed = true))
            val mockState: StateFlow<List<Hit>> = MutableStateFlow(products)

            // when
            coEvery { getAllProductsUseCase.invoke() } returns mockState
            viewModel.loadProducts()

            // then
            assert(viewModel.uiState.value is IProductUiState.Products)
        }
    }

    @Test
    fun `when api return error or blank Hit list show Empty content Screen`() {
        coroutinesTestRule.runBlockingTest {
            // given
            val getAllProductsUseCase = mockk<GetAllProductsUseCase>(relaxed = true)

            val viewModel = ProductsViewModel(
                getAllProductsUseCase,
                mockk(relaxed = true)
            )
            val mockState: StateFlow<List<Hit>?> = MutableStateFlow(null)

            // when
            coEvery { getAllProductsUseCase.invoke() } returns mockState
            viewModel.loadProducts()

            // then
            assert(viewModel.uiState.value is IProductUiState.NoProducts)
        }
    }

    @Test
    fun `when search input blank then show all Products`() {
        coroutinesTestRule.runBlockingTest {
            // given
            val getAllProductsUseCase = mockk<GetAllProductsUseCase>(relaxed = true)

            val viewModel = ProductsViewModel(
                getAllProductsUseCase,
                mockk(relaxed = true)
            )

            // when
            val products: MutableList<Hit> = mutableListOf()
            products.add(mockk<Hit>(relaxed = true).copy(name = "Hello"))
            products.add(mockk<Hit>(relaxed = true).copy(name = "TEST"))
            val mockState: StateFlow<List<Hit>> = MutableStateFlow(products)

            // when
            coEvery { getAllProductsUseCase.invoke() } returns mockState
            viewModel.loadProducts()
            viewModel.onSearchTextChanged("")

            // then
            assert( (viewModel.uiState.value as IProductUiState.Products).filteredProducts.size == products.size)
        }
    }

    @Test
    fun `when has search keyword then filter product with item containing keyword `() {
        coroutinesTestRule.runBlockingTest {
            // given
            val getAllProductsUseCase = mockk<GetAllProductsUseCase>(relaxed = true)

            val viewModel = ProductsViewModel(
                getAllProductsUseCase,
                mockk(relaxed = true)
            )

            // when
            val products: MutableList<Hit> = mutableListOf()
            products.add(mockk<Hit>(relaxed = true).copy(name = "Hello"))
            products.add(mockk<Hit>(relaxed = true).copy(name = "TEST"))
            val mockState: StateFlow<List<Hit>> = MutableStateFlow(products)

            // when
            coEvery { getAllProductsUseCase.invoke() } returns mockState
            viewModel.loadProducts()
            viewModel.onSearchTextChanged("he")
            delay(1000)

            // then
            assert( (viewModel.uiState.value as IProductUiState.Products).filteredProducts.size == 1)
        }
    }

    @Test
    fun `when Favourite then Product should be added to FavouriteSet`() {
        coroutinesTestRule.runBlockingTest {
            // given
            val getAllProductsUseCase = mockk<GetAllProductsUseCase>(relaxed = true)

            val viewModel = ProductsViewModel(
                getAllProductsUseCase,
                mockk(relaxed = true)
            )

            // when
            val products: MutableList<Hit> = mutableListOf()
            products.add(mockk<Hit>(relaxed = true).copy(id = "1", name = "Hello"))
            products.add(mockk<Hit>(relaxed = true).copy(id = "2", name = "TEST"))
            val mockState: StateFlow<List<Hit>> = MutableStateFlow(products)

            // when
            coEvery { getAllProductsUseCase.invoke() } returns mockState
            viewModel.loadProducts()
            viewModel.toggleFavorites("1")

            // then
            assert( (viewModel.uiState.value as IProductUiState.Products).favorites.contains("1"))
        }
    }

    @Test
    fun `when UnFavorite then Product should be Removed to FavouriteSet`() {
        coroutinesTestRule.runBlockingTest {
            // given
            val getAllProductsUseCase = mockk<GetAllProductsUseCase>(relaxed = true)

            val viewModel = ProductsViewModel(
                getAllProductsUseCase,
                mockk(relaxed = true)
            )

            // when
            val products: MutableList<Hit> = mutableListOf()
            products.add(mockk<Hit>(relaxed = true).copy(id = "1", name = "Hello"))
            products.add(mockk<Hit>(relaxed = true).copy(id = "2", name = "TEST"))
            val mockState: StateFlow<List<Hit>> = MutableStateFlow(products)

            // when
            coEvery { getAllProductsUseCase.invoke() } returns mockState
            viewModel.loadProducts()

            viewModel.toggleFavorites("1") // fav
            viewModel.toggleFavorites("1") // unfav

            // then
            assert( !(viewModel.uiState.value as IProductUiState.Products).favorites.contains("1"))
        }
    }

    @Test
    fun `when Filter with Favorites then Product with Id in FavSet Should be shown`() {
        coroutinesTestRule.runBlockingTest {
            // given
            val getAllProductsUseCase = mockk<GetAllProductsUseCase>(relaxed = true)

            val viewModel = ProductsViewModel(
                getAllProductsUseCase,
                mockk(relaxed = true)
            )

            // when
            val products: MutableList<Hit> = mutableListOf()
            products.add(mockk<Hit>(relaxed = true).copy(id = "1", name = "Hello"))
            products.add(mockk<Hit>(relaxed = true).copy(id = "2", name = "TEST"))
            val mockState: StateFlow<List<Hit>> = MutableStateFlow(products)

            coEvery { getAllProductsUseCase.invoke() } returns mockState
            viewModel.loadProducts()
            viewModel.toggleFavorites("1")
            viewModel.onFilterOptionClick(FAVORITE)

            delay(1000)

            // then
            assert( (viewModel.uiState.value as IProductUiState.Products).filteredProducts.all {
                (viewModel.uiState.value as IProductUiState.Products).favorites.contains(it.id)
            } )
        }
    }

}