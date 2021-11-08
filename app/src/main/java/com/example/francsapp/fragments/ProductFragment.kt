package com.example.francsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.francsapp.R
import com.example.francsapp.models.Product
import com.example.francsapp.viewmodels.ProductFragmentViewModel

class ProductFragment(product: Product) : Fragment() {

    private val viewModelProduct: ProductFragmentViewModel by viewModels()
    var product: Product = product
    lateinit var counterView: TextView
    lateinit var addProductToCart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_product, container, false)

        viewModelProduct.setSelectedProduct(product)

        var textName: TextView = view.findViewById(R.id.productDetailName)
        textName.text = product.name

        var textDescription: TextView = view.findViewById(R.id.productDetailDescription)
        textDescription.text = product.description

        var priceTV: TextView = view.findViewById(R.id.productPriceTV)
        priceTV.text = '$'.plus(product.price.toString())

        var imgView: ImageView = view.findViewById(R.id.productImgView)
        Glide
            .with(view)
            .load(product.imgUrl)
            .fitCenter()
            .into(imgView)
        var addButton: Button = view.findViewById(R.id.buttonAdd)
        addButton.setOnClickListener{
            viewModelProduct.addProduct()
        }
        var substractButton: Button = view.findViewById(R.id.buttonSubstract)
        substractButton.setOnClickListener{
            viewModelProduct.quitProduct()
        }
        counterView = view.findViewById(R.id.productCountTV)

        addProductToCart = view.findViewById(R.id.addToCart)
        addProductToCart.setOnClickListener{
            viewModelProduct.addProductToCart()
            redirectToHome()
        }

        var productBackArrow: ImageView = view.findViewById(R.id.productBackArrow)
        productBackArrow.setOnClickListener{
            redirectToHome()
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        viewModelProduct.counter.observe(viewLifecycleOwner, Observer {
            addProductToCart.isEnabled = it > 0
            counterView.text = it.toString()
        })
    }

    private fun redirectToHome(){
        getActivity()?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fragmentContainerView2,HomeFragment())
            commit()
        }
    }
}