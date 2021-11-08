package com.example.francsapp.models

import android.os.Parcel
import android.os.Parcelable

class CreditCard(): Parcelable {
    var cardNumber: String = ""
    var expDate: String = "00/00"
    var entity: String = ""
    var bank: String = ""
    var owner: String = ""
    var entityUrl: String = ""
    var isChecked: Boolean = false

    constructor(parcel: Parcel) : this(
    ) {
        cardNumber = parcel.readString()!!
        expDate = parcel.readString()!!
        entity = parcel.readString()!!
        bank = parcel.readString()!!
        owner = parcel.readString()!!
        entityUrl = parcel.readString()!!
    }

    init {
        this.cardNumber = cardNumber
        this.expDate = expDate
        this.entity = entity
        this.bank = bank
        this.owner = owner
        this.entityUrl = entityUrl
        this.isChecked = false
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<CreditCard> {
        override fun createFromParcel(parcel: Parcel): CreditCard {
            return CreditCard(parcel)
        }

        override fun newArray(size: Int): Array<CreditCard?> {
            return arrayOfNulls(size)
        }
    }
}