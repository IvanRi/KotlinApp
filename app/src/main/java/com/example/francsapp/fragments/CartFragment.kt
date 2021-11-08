package com.example.francsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.francsapp.R
import com.example.francsapp.adapter.CartAdapter
import com.example.francsapp.models.OrderItem
import com.example.francsapp.viewmodels.CartFragmentViewModel

class CartFragment : Fragment() {
    private lateinit var recCart: RecyclerView
    lateinit var totalCount: TextView
    private val viewModelCart: CartFragmentViewModel by viewModels()
    lateinit var payButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_cart, container, false)
        payButton = v.findViewById<Button>(R.id.buttonPay)

        payButton.setOnClickListener{
            getActivity()?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragmentContainerView2,PayFragment())
                commit()
            }
        }
        viewModelCart.getAllOrderItems()

        recCart = v.findViewById(R.id.cartContainer)

        totalCount = v.findViewById(R.id.cartTotal)
        return v
    }

    override fun onStart() {
        super.onStart()

        viewModelCart.itemOrderList.observe(viewLifecycleOwner, Observer {
            payButton.isEnabled = it.size > 0
            recCart.adapter = CartAdapter(it){
                    item: OrderItem ->
                run {
                    viewModelCart.deleteItem(item)
                    getActivity()?.supportFragmentManager?.beginTransaction()?.apply {
                        replace(R.id.fragmentContainerView2,CartFragment())
                        commit()
                    }
                }
            }
        })

        totalCount.text = viewModelCart.getTotalCount()

        recCart.setHasFixedSize(true)
        recCart.layoutManager = LinearLayoutManager(context)
    }
}