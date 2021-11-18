package com.example.francsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.francsapp.R
import com.example.francsapp.adapter.CartAdapter
import com.example.francsapp.adapter.HistoryAdapter
import com.example.francsapp.models.SavedOrder
import com.example.francsapp.viewmodels.HistoryViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class HistoryFragment : Fragment() {

    private val historyViewModel: HistoryViewModel by viewModels()
    lateinit var recHistory: RecyclerView
    var orderList = mutableListOf<SavedOrder>()
    lateinit var v: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        historyViewModel.getAllOrders()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_history, container, false)

        recHistory = v.findViewById(R.id.historyItemsRecycler)
        // Inflate the layout for this fragment
        return v
    }

    override fun onStart() {
        super.onStart()

        historyViewModel.orderList.observe(viewLifecycleOwner, Observer {
            orderList.clear()
            orderList.addAll(it)
            recHistory.adapter!!.notifyDataSetChanged()
        })


        recHistory.setHasFixedSize(true)

        recHistory.layoutManager = LinearLayoutManager(context)

        recHistory.adapter = HistoryAdapter(orderList) {

                val dialog = context?.let { BottomSheetDialog(it) }

                val view = layoutInflater.inflate(R.layout.bottom_sheet_product, null)
                var recHistoryDetail: RecyclerView = view.findViewById(R.id.otroIdDiferente)

                recHistoryDetail.setHasFixedSize(true)

                recHistoryDetail.layoutManager = (LinearLayoutManager(context))
                recHistoryDetail.adapter = CartAdapter(it.items, false){}
                val btnClose = view.findViewById<ImageView>(R.id.buttonDismiss)
                var codeTv: TextView = view.findViewById(R.id.productCodeBS)
                codeTv.text = it.orderCode.toString()
                var statePill = view.findViewById<TextView>(R.id.statePill)
                statePill.text = it.orderState!!.value
                var orderTotal: TextView = view.findViewById(R.id.orderTotal)
                orderTotal.text = '$'.plus(it.total.toString())
                btnClose.setOnClickListener {
                    dialog!!.dismiss()
                }
                dialog!!.setCancelable(false)

                dialog!!.setContentView(view)

                dialog!!.show()

        }
    }
}