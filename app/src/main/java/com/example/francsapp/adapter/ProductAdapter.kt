package com.example.francsapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.francsapp.R
import com.example.francsapp.models.Product

class ProductAdapter(
    var productList: MutableList<Product>,
    var onClick: (Int) -> Unit
): RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    class ProductHolder(v: View) : RecyclerView.ViewHolder(v){
        private var view: View = v
        fun setName (name: String){
            var textName: TextView = view.findViewById(R.id.productName)
            textName.text = name
        }

        fun setImgUrl (url: String){
            var imgView: ImageView = view.findViewById(R.id.productImage)
            Glide
                .with(view)
                .load(url)
                .fitCenter()
                .into(imgView)
        }

        fun setDescription(description: String){
            var descView: TextView = view.findViewById(R.id.productDescription)
            descView.text = description
        }

        fun setPrice(price: Double){
            var priceView: TextView = view.findViewById(R.id.productPrice)
            priceView.text = "$".plus(price.toString())
        }

        fun getCardView(): CardView {
            return view.findViewById(R.id.productCardView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product,parent,false)
        return (ProductHolder(view))
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        var product: Product = productList[position]
        holder.setName(product.name)
        holder.getCardView().setOnClickListener{
            onClick(position)
        }
        holder.setImgUrl(product.imgUrl)
        holder.setDescription(product.description)
        holder.setPrice(product.price)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}