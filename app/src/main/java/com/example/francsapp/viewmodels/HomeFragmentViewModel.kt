package com.example.francsapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.francsapp.models.DrinkType
import com.example.francsapp.models.Product
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragmentViewModel: ViewModel() {

    var firebaseDB = Firebase.firestore

    val productList = MutableLiveData<MutableList<Product>>()
    val drinkTypesList = MutableLiveData<MutableList<DrinkType>>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        updateProductList()
        getDrinkTypesList()
        this.isLoading.value = true
    }

    private fun updateProductList () {
        val docRef = firebaseDB.collection("productos")
        docRef.get()
            .addOnSuccessListener { documents ->
                isLoading.value = false
                var products = mutableListOf<Product>()
                for (doc in documents) {
                    val product = doc.toObject(Product::class.java)
                    products.add(product)
                }
                productList.value = products
            }
            .addOnFailureListener { exception ->
                isLoading.value = false
                Log.d("fail", "get failed with ", exception)
            }
    }

    private fun getDrinkTypesList () {
        val docRef = firebaseDB.collection("drinkTypes")
        docRef.get()
            .addOnSuccessListener { documents ->
                var types = mutableListOf<DrinkType>()
                for (doc in documents) {
                    val type = doc.toObject(DrinkType::class.java)
                    types.add(type)
                }
                drinkTypesList.value = types
            }
            .addOnFailureListener { exception ->
                Log.d("fail", "get failed with ", exception)
            }
    }
}