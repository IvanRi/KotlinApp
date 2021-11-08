package com.example.francsapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SavedOrder(var userId: Int, var items: MutableList<OrderItem>, var payMethod: CreditCard, var orderCode: Long, var orderState: OrderState): Parcelable {

}