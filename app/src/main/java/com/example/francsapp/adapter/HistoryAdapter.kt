package com.example.francsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.francsapp.R
import com.example.francsapp.models.OrderState
import com.example.francsapp.models.SavedOrder

class HistoryAdapter(
    var orderList: MutableList<SavedOrder>,
    var onClick: (SavedOrder) -> Unit
): RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {

    class HistoryHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun setDate (date: String){
            var textDate: TextView = view.findViewById(R.id.historyItemDate)
            textDate.text = date
        }

        fun setTotal (total: Double){
            var priceTotal: TextView = view.findViewById(R.id.historyItemTotal)
            priceTotal.text = '$'.plus(total.toString())
        }

        fun setState (state: OrderState){
            var stateTextView: TextView = view.findViewById(R.id.historyItemState)
            stateTextView.text = state.value
        }

        fun setCode (code: Long) {
            var codeText: TextView = view.findViewById(R.id.historyItemCode)
            var codeStr = code.toString()
            codeText.text = codeStr.slice(3..(codeStr.length-1))
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryAdapter.HistoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.historial_item,parent,false)
        return (HistoryAdapter.HistoryHolder(view))
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        var item = orderList[position]
        holder.setCode(item.orderCode)
        holder.setDate(item.date)
        holder.setState(item.orderState!!)
        holder.setTotal(item.total)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

}