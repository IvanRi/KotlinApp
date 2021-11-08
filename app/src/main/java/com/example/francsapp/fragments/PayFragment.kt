package com.example.francsapp.fragments

import android.os.Bundle
import android.util.Log
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
import com.example.francsapp.adapter.CreditCardAdapter
import com.example.francsapp.models.CreditCard
import com.example.francsapp.viewmodels.PayFragmentViewModel

class PayFragment : Fragment() {
    private lateinit var recPay: RecyclerView
    lateinit var v: View
    val payViewModel: PayFragmentViewModel by viewModels()
    var cardList = mutableListOf<CreditCard>()
    lateinit var payAndFinalButton: Button
    lateinit var totalPrice: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_pay, container, false)
        totalPrice = v.findViewById(R.id.cartTotal)
        payAndFinalButton = v.findViewById(R.id.payAndFinalButton)
        payAndFinalButton.setOnClickListener(){
            payViewModel.payPurchase()
            getActivity()?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragmentContainerView2,HomeFragment())
                commit()
            }
        }
        recPay = v.findViewById(R.id.cardsContainer)
        // Inflate the layout for this fragment
        return v
    }

    override fun onStart() {
        super.onStart()
        payViewModel.cards.observe(viewLifecycleOwner, Observer {
            cardList.clear()
            cardList.addAll(it)
            recPay.adapter?.notifyDataSetChanged()
        })

        payViewModel.creditCardSelected.observe(viewLifecycleOwner, Observer {
            payAndFinalButton.isEnabled = it != null
        })
        totalPrice.text = "$".plus(payViewModel.getTotalPrice().toString())

            recPay.setHasFixedSize(true)
        recPay.layoutManager = LinearLayoutManager(context)
        recPay.adapter = CreditCardAdapter(cardList){
            payViewModel.setSelectedCard(it)
        }
    }
}