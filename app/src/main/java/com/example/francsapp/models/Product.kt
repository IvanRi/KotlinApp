package com.example.francsapp.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Product(
      var productId: Int = 0,
      var name: String = "",
      var stock: Int = 0,
      var description: String = "",
      var imgUrl: String = "",
      var price: Double = 0.0,
      var typeId: Int = 0) : Parcelable {

    fun Product(productId: Int,
        name: String,
        stock: Int,
        description: String,
        imgUrl: String,
        price: Double,
        typeId: Int) {
        this.productId = productId
        this.name=  name
        this.stock = stock
        this.description = description
        this.imgUrl = imgUrl
        this.price = price
        this.typeId = typeId
    }
}