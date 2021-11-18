package com.example.francsapp.viewmodels

import android.app.DownloadManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.francsapp.models.SavedOrder
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HistoryViewModel: ViewModel() {

    var firebaseDB = Firebase.firestore
    val orderList = MutableLiveData<MutableList<SavedOrder>>()

    fun getAllOrders(){
        val docRef = firebaseDB.collection("orders").orderBy("orderCode", Query.Direction.DESCENDING)
        docRef.get()
            .addOnSuccessListener { documents ->
                var orders = mutableListOf<SavedOrder>()
                for (doc in documents) {
                    println(doc.data)
                    val order = doc.toObject(SavedOrder::class.java)
                    orders.add(order)
                }
                orderList.value = orders
            }
            .addOnFailureListener { exception ->
                Log.d("fail", "get failed with ", exception)
            }
    }
}