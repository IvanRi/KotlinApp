package com.example.francsapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.francsapp.database.appDatabase
import com.example.francsapp.database.itemDao
import com.example.francsapp.models.OrderItem

class CartFragmentViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var db: appDatabase
    lateinit var itemDao: itemDao
    var itemOrderList = MutableLiveData<MutableList<OrderItem>>()

    fun getAllOrderItems (){
        db = appDatabase.getAppDataBase(getApplication())!!
        itemDao = db.itemsDao()
        itemOrderList.value = itemDao.getAllItem()
    }

    fun getTotalCount(): String {
        return "$".plus(itemDao.getTotalCount().toString())
    }

    fun deleteItem(item: OrderItem){
        itemDao.deleteItem(item)
    }
}