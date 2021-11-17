package com.example.francsapp.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.francsapp.R
import com.example.francsapp.adapter.DrinkTypesAdapter
import com.example.francsapp.adapter.ProductAdapter
import com.example.francsapp.models.DrinkType
import com.example.francsapp.models.Product
import com.example.francsapp.viewmodels.HomeFragmentViewModel

class HomeFragment : Fragment() {
    lateinit var v: View
    private lateinit var recProducts: RecyclerView
    lateinit var recTypes: RecyclerView
    var productList = mutableListOf<Product>()
    var typesList = mutableListOf<DrinkType>()
    lateinit var loader: ProgressBar
    private val viewModelHome: HomeFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_home, container, false)

        recProducts = v.findViewById(R.id.productList)
        recTypes = v.findViewById(R.id.typeListView)

        loader = v.findViewById(R.id.progressBarProducts)

        return v
    }

    override fun onStart() {
        super.onStart()
        recTypes.setHasFixedSize(true)
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        viewModelHome.selectedType.observe(viewLifecycleOwner, Observer {
                var homeTitle: TextView = v.findViewById(R.id.homeTitle)
                homeTitle.text = it.name
        })

        viewModelHome.productList.observe(viewLifecycleOwner, Observer {
                products ->
            run {
                this.productList.clear()
                this.productList.addAll(products)
                recProducts.adapter!!.notifyDataSetChanged()
            }
        })

        viewModelHome.drinkTypesList.observe(viewLifecycleOwner, Observer {
                drinkTypes ->
            run {
                this.typesList.clear()
                this.typesList.addAll(drinkTypes)
                recTypes.adapter!!.notifyDataSetChanged()
            }
        })

        viewModelHome.isLoading.observe(viewLifecycleOwner, Observer {
            loader.visibility = if(it) View.VISIBLE else View.GONE
        })
        recTypes.layoutManager = layoutManager
        recTypes.adapter = DrinkTypesAdapter(typesList){
            viewModelHome.getProductByDrinkType(it)
        }
        recProducts.setHasFixedSize(true)

        recProducts.layoutManager = LinearLayoutManager(context)

        recProducts.adapter = ProductAdapter(productList){
            redirectOnClick(it)
        }
    }

    private fun redirectOnClick(pos: Int) {
        getActivity()?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fragmentContainerView2,ProductFragment(productList[pos], HomeFragment()))
            commit()
        }
    }

}