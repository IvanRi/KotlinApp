package com.example.francsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.francsapp.R
import com.example.francsapp.adapter.HistoryAdapter
import com.example.francsapp.adapter.ProductAdapter
import com.example.francsapp.models.SavedOrder
import com.example.francsapp.viewmodels.HistoryViewModel

class HistoryFragment : Fragment() {

    private val historyViewModel: HistoryViewModel by viewModels()
    lateinit var recHistory: RecyclerView
    var orderList = mutableListOf<SavedOrder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        historyViewModel.getAllOrders()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.fragment_history, container, false)

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

        recHistory.adapter = HistoryAdapter(orderList){
        }
    }
}