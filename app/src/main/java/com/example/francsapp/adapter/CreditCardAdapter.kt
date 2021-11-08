package com.example.francsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.francsapp.R
import com.example.francsapp.models.CreditCard

class CreditCardAdapter(
    var cardList: MutableList<CreditCard>,
    var onClick: (Int) -> Unit
): RecyclerView.Adapter<CreditCardAdapter.CreditCardHolder>() {

    class CreditCardHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun setCardOwner (name: String){
            var cardOwner: TextView = view.findViewById(R.id.cardOwner)
            cardOwner.text = name
        }

        fun setEntityImg (url: String){
            var imgContainer: ImageView = view.findViewById(R.id.cardEntityImg)
            Glide
                .with(view)
                .load(url)
                .fitCenter()
                .into(imgContainer)
        }

        fun setCardNumber (n: String){
            var cardNumber: TextView = view.findViewById(R.id.cardNumber)
            cardNumber.text = "**** **** ****".plus(n.slice(14..(n.length-1)))
        }

        fun setRadioButtonValue (isChecked: Boolean){
            var button: RadioButton = view.findViewById(R.id.cardRadioButton)
            button.isChecked = isChecked
        }

        fun getRadioButton(): RadioButton{
            var button: RadioButton = view.findViewById(R.id.cardRadioButton)
            return button
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditCardAdapter.CreditCardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.credit_card,parent,false)
        return (CreditCardAdapter.CreditCardHolder(view))
    }

    override fun onBindViewHolder(holder: CreditCardAdapter.CreditCardHolder, position: Int) {
        var card: CreditCard = cardList[position]
        holder.setCardOwner(card.owner)
        holder.setEntityImg(card.entityUrl)
        holder.setCardNumber(card.cardNumber)
        holder.setRadioButtonValue(card.isChecked)
        holder.getRadioButton().setOnClickListener{
            onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }
}