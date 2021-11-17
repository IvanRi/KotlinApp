package com.example.francsapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.francsapp.database.FavoriteItemDao
import com.example.francsapp.database.appDatabase
import com.example.francsapp.database.itemDao
import com.example.francsapp.models.Product
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    var firebaseDB = Firebase.firestore
    var db: appDatabase
    var favItemDao: FavoriteItemDao
    // val favoritesIds =  MutableLiveData<MutableList<Int>>()
    val productList = MutableLiveData<MutableList<Product>>()

    init {
        db = appDatabase.getAppDataBase(getApplication())!!
        favItemDao = db.favoriteItemDao()
        getAllFavoriteProducts()
    }

    fun getAllFavoriteProducts(){
        val docRef = firebaseDB.collection("productos")
        var favorites = favItemDao.getAllItem()
        docRef.get()
            .addOnSuccessListener { documents ->
                var products = mutableListOf<Product>()
                for (doc in documents) {
                    val product = doc.toObject(Product::class.java)
                    if(favorites != null){
                        var isFav = favorites.filter {
                            it.productId.equals(product.productId)
                        }
                        if(isFav.isNotEmpty()){
                            products.add(product)
                        }
                    }
                }
                productList.value = products
            }
            .addOnFailureListener { exception ->
                Log.d("fail", "get failed with ", exception)
            }
    }
}