package com.example.francsapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.francsapp.R
import com.example.francsapp.models.DrinkType

class DrinkTypesAdapter(
    var typesList: MutableList<DrinkType>,
    var onClick: (DrinkType)-> Unit
): RecyclerView.Adapter<DrinkTypesAdapter.TypesHolder>() {

    class TypesHolder(v: View): RecyclerView.ViewHolder(v){
        private var view: View = v

        fun setTagName(typeName: String){
            var textView: TextView = view.findViewById(R.id.drinkName)
            textView.text = typeName
        }

        fun getTag(): TextView {
            return view.findViewById(R.id.drinkName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tag,parent, false )
        return (TypesHolder(view))
    }

    override fun onBindViewHolder(holder: TypesHolder, position: Int) {
        var drinkType: DrinkType = typesList[position]

        holder.setTagName(drinkType.name)
        holder.getTag().setOnClickListener(){
            onClick(drinkType)
        }
    }

    override fun getItemCount(): Int {
        return typesList.size
    }
}