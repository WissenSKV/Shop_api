package com.example.myshop_api.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop_api.R
import com.example.myshop_api.ViewModel.productsViewModel
import com.example.myshop_api.databinding.ActivityMainBinding

import android.content.Intent

import com.example.myshop_api.model.product
import com.example.myshop_api.ui.ProductsAdapter


class MainActivity : AppCompatActivity(), ProductsAdapter.OnProductClickListener {
    private val productsViewModel: productsViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 1)
        binding.rvproducts.layoutManager = layoutManager
    }

    override fun onResume() {
        super.onResume()
        productsViewModel.fetchProducts()
        productsViewModel.producctLiveData.observe(this, Observer { productList ->

            val productAdapter = ProductsAdapter(productList ?: emptyList(), this)
            binding.rvproducts.adapter = productAdapter


        })
        productsViewModel.errorLiveData.observe(this, Observer { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
        })
    }

    override fun onProductClick(product: product) {
        val intent = Intent(this, DetailedActivity::class.java)
        intent.putExtra("productId", product.id)
        startActivity(intent)
    }
}