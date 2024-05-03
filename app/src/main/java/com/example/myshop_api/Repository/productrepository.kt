package com.example.myshop_api.Repository

import com.example.myshop_api.api.ApiClient
import com.example.myshop_api.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.myshop_api.model.ProductsResponse
import com.example.myshop_api.model.product
import retrofit2.Response

class productrepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun getProducts():Response<ProductsResponse>{
        return withContext(Dispatchers.IO){
            apiClient.getProducts()
        }
    }
    suspend fun getProductById(productId: Int): Response<product> {
        return apiClient.getProductsbyId(productId)
    }

}