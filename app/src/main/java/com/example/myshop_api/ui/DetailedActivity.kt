package com.example.myshop_api.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myshop_api.R
import com.example.myshop_api.databinding.DetailedActivityBinding
import com.example.myshop_api.model.product
import com.example.myshop_api.Repository.productrepository
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class DetailedActivity : AppCompatActivity() {
    private lateinit var binding: DetailedActivityBinding
    private val productRepository = productrepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailedActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.getIntExtra("productId", -1)
        if (productId != -1) {
            loadProduct(productId)
        } else {
            Toast.makeText(this, "Error: Product ID not found", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun loadProduct(productId: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                productRepository.getProductById(productId)
            }
            if (response.isSuccessful) {
                val product = response.body()
                if (product != null) {
                    bindProduct(product)
                } else {
                    Toast.makeText(this@DetailedActivity, "Product not found", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
                Toast.makeText(this@DetailedActivity, "Failed to load product", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun bindProduct(product: product) {
        binding.titleTextView.text = product.title
        binding.descriptionTextView.text = product.description
        binding.priceTextView.text = "Price: ${product.price} $"
        binding.discountTextView.text = "Discount: ${product.discountPercentage}%"
        binding.ratingTextView.text = "Rating: ${product.rating}"
        binding.stockTextView.text = "Stock: ${product.stock}"
        binding.brandTextView.text = "Brand: ${product.brand}"
        binding.categoryTextView.text = "Category: ${product.category}"
        Picasso.get()
            .load(product.thumbnail)
            .placeholder(R.drawable.ic_launcher_foreground)
            .resize(280, 280)
            .transform(CropCircleTransformation())
            .into(binding.imageView)
    }

}
