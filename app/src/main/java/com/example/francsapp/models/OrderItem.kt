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
    var name: String = "",
    @ColumnInfo(name="units")
    var units: Int = 0,
    @ColumnInfo(name="price")
    var price: Double = 0.0,
    @PrimaryKey
    @ColumnInfo(name="itemId")
    var itemId: Int = 0
        ): Parcelable {
    fun OrderItem(){}
}