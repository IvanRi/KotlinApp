package com.example.francsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.francsapp.R
import com.example.francsapp.models.OrderItem
import android.os.Build
import androidx.fragment.app.FragmentTransaction


class CartAdapter(
    var itemList: MutableList<OrderItem>,
    var onClick: (OrderItem) -> Unit
): RecyclerView.Adapter<CartAdapter.CartHolder>() {

    class CartHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun setName (name: String){
            var textName: TextView = view.findViewById(R.id.orderItemName)
            textName.text = name
        }

        fun setUnits (units: Int){
            var priceName: TextView = view.findViewById(R.id.orderItemUnits)
            priceName.text = 'x'.plus(units.toString())
        }

        fun setPrice (price: Double){
            var priceTextView: TextView = view.findViewById(R.id.orderItemPrice)
            priceTextView.text = '$'.plus(price.toString())
        }

        fun getDeleteButton (): ImageView{
            return view.findViewById(R.id.deleteButton)
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item,parent,false)
        return (CartHolder(view))
    }

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        var item: OrderItem = itemList[position]
        holder.setName(item.name)
        holder.setUnits(item.units)
        holder.setPrice(item.price)
        holder.getDeleteButton().setOnClickListener{
            onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}