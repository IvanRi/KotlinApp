package com.example.francsapp.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.francsapp.database.appDatabase
import com.example.francsapp.database.itemDao
import com.example.francsapp.models.CreditCard
import com.example.francsapp.models.OrderState
import com.example.francsapp.models.SavedOrder
import com.example.francsapp.utils.DateUtils.Companion.obtenerFechaConFormato
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PayFragmentViewModel(application: Application) : AndroidViewModel(application) {

    var firebaseDB = Firebase.firestore

    var roomDB: appDatabase
    var itemDao: itemDao

    val cards = MutableLiveData<MutableList<CreditCard>>()
    val isLoading = MutableLiveData<Boolean>()
    val creditCardSelected = MutableLiveData<CreditCard>()

    init {
        getPaymentCards()
        roomDB = appDatabase.getAppDataBase(getApplication())!!
        itemDao = roomDB.itemsDao()
        creditCardSelected.value = null
        this.isLoading.value = true
    }

    private fun getPaymentCards () {
        val docRef = firebaseDB.collection("creditCards")
        docRef.get()
            .addOnSuccessListener { documents ->
                isLoading.value = false
                var cardList = mutableListOf<CreditCard>()
                for (doc in documents) {
                    val card = doc.toObject(CreditCard::class.java)
                    cardList.add(card)
                }
                cards.value = cardList
            }
            .addOnFailureListener { exception ->
                isLoading.value = false
                Log.d("fail", "get failed with ", exception)
            }
    }

    fun setSelectedCard(index: Int){
        var auxCardList = mutableListOf<CreditCard>()
        for (card in cards.value!!){
            card.isChecked = false
            auxCardList.add(card)
        }

        auxCardList[index].isChecked = true
        creditCardSelected.value = auxCardList[index]
        cards.value = auxCardList
    }

    fun getTotalPrice (): Double{
        var result = itemDao.getTotalCount()
        return result
    }

    fun payPurchase(){
        var date = obtenerFechaConFormato("yyyy-MM-dd","America/Argentina/Buenos_Aires")
        var items = itemDao.getAllItem()
        var order = SavedOrder(1, items, creditCardSelected.value!!,System.currentTimeMillis(),OrderState.IN_PREPARATION,
            date.toString(), getTotalPrice()
        )
        firebaseDB.collection("orders").add(order)
        roomDB.clearAllTables()
        Toast.makeText(getApplication(),"Su pedido fue creado con exito!", Toast.LENGTH_LONG).show()
    }
}