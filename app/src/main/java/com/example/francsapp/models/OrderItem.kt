package com.example.francsapp.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="items")
class OrderItem(
    @ColumnInfo(name="name")
    var name: String,
    @ColumnInfo(name="units")
    var units: Int,
    @ColumnInfo(name="price")
    var price: Double,
    @PrimaryKey
    @ColumnInfo(name="itemId")
    var itemId: Int
        ): Parcelable {
}