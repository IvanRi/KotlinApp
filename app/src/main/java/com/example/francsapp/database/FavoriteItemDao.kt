package com.example.francsapp.database

import androidx.room.*
import com.example.francsapp.models.FavoriteItem

@Dao
interface FavoriteItemDao {
    @Query("SELECT * FROM favorites where producId=:id")
    fun getItemById(id: Int): FavoriteItem

    @Query("SELECT * FROM favorites")
    fun getAllItem(): MutableList<FavoriteItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: FavoriteItem)

    @Delete
    fun deleteItem(item: FavoriteItem)
}