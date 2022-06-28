package com.neosofttech.sowingo.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.neosofttech.sowingo.di.annotations.WorkDispatcher
import com.neosofttech.sowingo.global.ENDPOINT
import com.neosofttech.sowingo.product.data.IProductService
import com.neosofttech.sowingo.product.data.ProductServiceImpl
import com.neosofttech.sowingo.product.domain.DefaultProductRepository
import com.neosofttech.sowingo.product.domain.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @WorkDispatcher
    fun provideWorkDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    @Provides
    fun provideProductService(retrofit: Retrofit): IProductService = ProductServiceImpl(retrofit)

    @Provides
    fun provideProductRepository(productService: IProductService): ProductRepository =
        DefaultProductRepository(productService)
}