package com.example.francsapp.database

import androidx.room.*
import com.example.francsapp.models.OrderItem

@Dao
interface itemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(orderItem: OrderItem)

    @Update
    fun updateItem(orderItem: OrderItem)

    @Delete
    fun deleteItem(orderItem: OrderItem)

    @Query("SELECT * FROM items")
    fun getAllItem(): MutableList<OrderItem>

    @Query("SELECT SUM(price) FROM items")
    fun getTotalCount(): Double

    @Query("SELECT * FROM items WHERE itemId=:id ")
    fun getSingleItem(id: Int): OrderItem
}