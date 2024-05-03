package com.example.myshop_api.api

import com.example.myshop_api.model.ProductsResponse
import com.example.myshop_api.model.product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/product")
    suspend fun getProducts(): Response<ProductsResponse>

    @GET("/product/{id}")
    suspend fun getProductsbyId(@Path("id") productId: Int): Response<product>
}