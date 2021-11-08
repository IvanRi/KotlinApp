package com.example.francsapp.models

import android.os.Parcel
import android.os.Parcelable

class Product() : Parcelable {
    constructor(parcel: Parcel) : this() {
    }
    var productId: Int = 0
    var name: String = ""
    var stock: Int = 0
    var description: String = ""
    var imgUrl: String = ""
    var price: Double = 0.0

    init {
        this.productId = productId
        this.name=  name
        this.stock = stock
        this.description = description
        this.imgUrl = imgUrl
        this.price = price
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}