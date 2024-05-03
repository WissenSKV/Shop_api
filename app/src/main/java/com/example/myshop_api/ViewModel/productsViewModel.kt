package com.example.myshop_api.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshop_api.Repository.productrepository
import com.example.myshop_api.model.product
import kotlinx.coroutines.launch

class productsViewModel:ViewModel (){

    val productRepo =productrepository()
    val producctLiveData = MutableLiveData<List<product>>()
    val errorLiveData = MutableLiveData<String>()
    fun fetchProducts(){
        viewModelScope.launch{
            val response = productRepo.getProducts()
            if (response.isSuccessful){
                producctLiveData.postValue(response.body()?.products)
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

}
