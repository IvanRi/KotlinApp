package com.example.francsapp.models

import android.os.Parcel
import android.os.Parcelable

class DrinkType ():Parcelable {
    var name: String = ""
    var typeId: Int = 0

    constructor(parcel: Parcel) : this(
    ) {
        name = parcel.readString()!!
        typeId = parcel.readInt()
    }

    constructor(typeName: String,typeId:Int) : this() {
        this.name = typeName
        this.typeId = typeId
    }

    init{
        this.name = name
        this.typeId = typeId
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.writeString(name);
    }

    companion object CREATOR : Parcelable.Creator<DrinkType> {
        override fun createFromParcel(parcel: Parcel): DrinkType {
            return DrinkType(parcel)
        }

        override fun newArray(size: Int): Array<DrinkType?> {
            return arrayOfNulls(size)
        }
    }
}