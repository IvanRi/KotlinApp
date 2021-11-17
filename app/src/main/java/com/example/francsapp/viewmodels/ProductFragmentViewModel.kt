package com.example.francsapp.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.francsapp.database.FavoriteItemDao
import com.example.francsapp.database.appDatabase
import com.example.francsapp.database.itemDao
import com.example.francsapp.models.FavoriteItem
import com.example.francsapp.models.OrderItem
import com.example.francsapp.models.Product

class ProductFragmentViewModel(application: Application) : AndroidViewModel(application) {

    val counter = MutableLiveData<Int>()
    var db: appDatabase
    var itemDao: itemDao
    var favoriteDao: FavoriteItemDao
    lateinit var currentProduct: Product
    var isCharged: Boolean = false
    val favoriteItemList = MutableLiveData<MutableList<FavoriteItem>>()

    init {
        db = appDatabase.getAppDataBase(getApplication())!!
        itemDao = db.itemsDao()
        favoriteDao = db.favoriteItemDao()
        counter.value = 0
        getAllFavoriteItems()
    }

    fun addProduct() {
        if(counter.value!! < currentProduct.stock) {
            counter.value = counter.value!! + 1
        }
    }

    fun quitProduct() {
        if( counter.value!! > 0) {
            counter.value = counter.value!! - 1
        }
    }

    private fun getTotalPrice (price: Double, units:Double): Double{
        var result = price * units
        return result
    }

    fun setSelectedProduct (p: Product){
        var orderItem = itemDao.getSingleItem(p.productId)
        if(orderItem != null){
            isCharged = true
            counter.value = orderItem.units
        }
        currentProduct = p
    }

    fun addProductToCart(){
        var itemToInsert = OrderItem(currentProduct.name,counter.value!!,getTotalPrice(currentProduct.price,counter.value!!.toDouble()),currentProduct.productId)
        if(isCharged){
            itemDao?.updateItem(itemToInsert)
            Toast.makeText(getApplication(),"Se modifico el producto en tu carrito!", Toast.LENGTH_SHORT).show()
        } else {
            itemDao?.insertItem(itemToInsert)
            Toast.makeText(getApplication(),"Se agrego el producto a tu carrito!", Toast.LENGTH_SHORT).show()
        }
    }

    fun getAllFavoriteItems () {
        var list = favoriteDao.getAllItem()
        favoriteItemList.value = list
    }

    fun toggleProductFavorite (productId: Int){
        var itemToInsert = FavoriteItem(productId)
        var hasItem = favoriteDao.getItemById(productId)
        if(hasItem == null){
            favoriteDao.insertItem(itemToInsert)
            getAllFavoriteItems()
        } else {
            favoriteDao.deleteItem(itemToInsert)
            getAllFavoriteItems()
        }
    }
}