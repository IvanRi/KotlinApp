package com.example.francsapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="favorites")
class FavoriteItem(
    @PrimaryKey
    @ColumnInfo(name="producId")
    var productId: Int = 0
) {
}