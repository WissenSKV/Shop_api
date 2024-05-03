package com.example.myshop_api.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop_api.R
import com.example.myshop_api.databinding.RecyclerBinding
import com.example.myshop_api.model.product
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class ProductsAdapter(private val productlist: List<product>, private val listener: OnProductClickListener) : RecyclerView.Adapter<ProductViewHolder>() {

    interface OnProductClickListener {
        fun onProductClick(product: product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = RecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = productlist[position]
        val binding = holder.binding
        binding.textView.text = currentProduct.title
        binding.textView2.text = currentProduct.price.toString()+"$"
        binding.textView3.text = currentProduct.category

        Picasso.get()
            .load(currentProduct.thumbnail)
            .placeholder(R.drawable.ic_launcher_foreground)
            .resize(280,280)
            .transform(CropCircleTransformation())
            .into(binding.imageView)

        binding.root.setOnClickListener { listener.onProductClick(currentProduct) }
    }

    override fun getItemCount(): Int {
        return productlist.size
    }
}

class ProductViewHolder(var binding: RecyclerBinding) : RecyclerView.ViewHolder(binding.root)
