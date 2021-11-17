package com.example.francsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.francsapp.R
import com.example.francsapp.adapter.ProductAdapter
import com.example.francsapp.models.FavoriteItem
import com.example.francsapp.models.Product
import com.example.francsapp.viewmodels.FavoritesViewModel

class FavoritesFragment : Fragment() {

    var favProducts = mutableListOf<Product>()
    val favViewModel: FavoritesViewModel by viewModels()
    lateinit var recFav: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_favorites, container, false)
        // Inflate the layout for this fragment
        recFav = view.findViewById(R.id.recViewFav)
        return view
    }

    override fun onStart() {
        super.onStart()

        favViewModel.getAllFavoriteProducts()

        recFav.setHasFixedSize(true)

        recFav.layoutManager = LinearLayoutManager(context)

        recFav.adapter = ProductAdapter(favProducts){
            redirectOnClick(it)
        }
        favViewModel.productList.observe(viewLifecycleOwner, Observer {
            favProducts.clear()
            favProducts.addAll(it)
            recFav.adapter!!.notifyDataSetChanged()
        })
    }

    private fun redirectOnClick(pos: Int) {
        getActivity()?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fragmentContainerView2,ProductFragment(favProducts[pos], FavoritesFragment()))
            commit()
        }
    }
}