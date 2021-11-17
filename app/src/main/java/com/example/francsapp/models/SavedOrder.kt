package com.example.francsapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat

@Parcelize
data class SavedOrder(
    var userId: Int = 0,
    var items: MutableList<OrderItem>? = null,
    var payMethod: CreditCard? = null,
    var orderCode: Long = 0,
    var orderState: OrderState? = null,
    var date: String = "",
    var total: Double = 0.0
    ): Parcelable {
        fun SavedOrder(){}
}