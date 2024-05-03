package com.example.myshop_api.model

data class ProductsResponse(
    var products:List<product>,
    var total:Int,
    var skip:Int,
    var limit:Int

)
