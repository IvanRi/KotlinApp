package com.example.francsapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.francsapp.models.DrinkType
import com.example.francsapp.models.Product
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragmentViewModel: ViewModel() {

    var firebaseDB = Firebase.firestore

    val productList = MutableLiveData<MutableList<Product>>()
    val drinkTypesList = MutableLiveData<MutableList<DrinkType>>()
    val isLoading = MutableLiveData<Boolean>()
    var selectedType = MutableLiveData<DrinkType>()
    var defaultDrinkType = DrinkType("Nuestros productos", 100)

    init {
        updateProductList()
        getDrinkTypesList()
        this.isLoading.value = true
        selectedType.value = defaultDrinkType
    }

    private fun chargeProducts (){
        var product = Product(123,"Coca-cola 2.15Lts",100,"Descripcion del producto detallada Son vinos jóvenes, frescos, mostrar las características y virtudes particulares de los diferentes racimos que intervienen en cada corte o assemblage cumplen con eficacia esa promesa.","https://firebasestorage.googleapis.com/v0/b/francs-app.appspot.com/o/coca.jpg?alt=media&token=19a56f09-c6b9-4860-86eb-7c26da0ffcea",220.0,5)
        var product2 = Product(125,"Fanta 2.15Lts",100,"Descripcion del producto detallada Son vinos jóvenes, frescos, mostrar las características y virtudes particulares de los diferentes racimos que intervienen en cada corte o assemblage cumplen con eficacia esa promesa.","https://firebasestorage.googleapis.com/v0/b/francs-app.appspot.com/o/fanta.jpg?alt=media&token=76b732b5-13cc-4a7d-9d4e-a0ab951eb84a",210.0,5)
        var product3 = Product(126,"Seven up 2.15Lts",100,"Descripcion del producto detallada Son vinos jóvenes, frescos, mostrar las características y virtudes particulares de los diferentes racimos que intervienen en cada corte o assemblage cumplen con eficacia esa promesa.","https://firebasestorage.googleapis.com/v0/b/francs-app.appspot.com/o/seven.jpg?alt=media&token=50c93a9f-9565-4b97-9510-c2b86635bc71",200.0,5)
        var product4 = Product(127,"Agua Villa del sur 2.15Lts",100,"Descripcion del producto detallada Son vinos jóvenes, frescos, mostrar las características y virtudes particulares de los diferentes racimos que intervienen en cada corte o assemblage cumplen con eficacia esa promesa.","https://firebasestorage.googleapis.com/v0/b/francs-app.appspot.com/o/villadelsur.jpg?alt=media&token=522804ed-0814-4fa8-8c12-3424cec84266",100.0,2)
        var product5 = Product(128,"Agua Villamanaos 2.15Lts",100,"Descripcion del producto detallada Son vinos jóvenes, frescos, mostrar las características y virtudes particulares de los diferentes racimos que intervienen en cada corte o assemblage cumplen con eficacia esa promesa.","https://firebasestorage.googleapis.com/v0/b/francs-app.appspot.com/o/manaos.jpg?alt=media&token=c953d785-5ab9-451f-a08e-b33e4acc30ed",50.0,2)
        var product6 = Product(129,"Jaggermeister 750Ml",100,"Descripcion del producto detallada Son vinos jóvenes, frescos, mostrar las características y virtudes particulares de los diferentes racimos que intervienen en cada corte o assemblage cumplen con eficacia esa promesa.","https://firebasestorage.googleapis.com/v0/b/francs-app.appspot.com/o/jagger.webp?alt=media&token=427097bc-bf31-4b71-8df8-5fb46e754f2f",1050.0,3)
        var product7 = Product(1290,"jack Daniels 750Ml",100,"Descripcion del producto detallada Son vinos jóvenes, frescos, mostrar las características y virtudes particulares de los diferentes racimos que intervienen en cada corte o assemblage cumplen con eficacia esa promesa.","https://firebasestorage.googleapis.com/v0/b/francs-app.appspot.com/o/jack.webp?alt=media&token=e19c066a-a8db-45e6-b9be-e9684c3ddbcf",1550.0,3)
        var product8 = Product(1291,"Beefeater 750Ml",100,"Descripcion del producto detallada Son vinos jóvenes, frescos, mostrar las características y virtudes particulares de los diferentes racimos que intervienen en cada corte o assemblage cumplen con eficacia esa promesa.","https://firebasestorage.googleapis.com/v0/b/francs-app.appspot.com/o/beefeater.webp?alt=media&token=6968ea28-0c9e-406b-aea0-842f737d2699",1850.0,3)
        var product9 = Product(1292,"Absolut raspberri 1Lts",100,"Descripcion del producto detallada Son vinos jóvenes, frescos, mostrar las características y virtudes particulares de los diferentes racimos que intervienen en cada corte o assemblage cumplen con eficacia esa promesa.","https://firebasestorage.googleapis.com/v0/b/francs-app.appspot.com/o/absolut.webp?alt=media&token=31f5a9b1-8f5c-4c4a-8dd9-4afd9a96f521",1850.0,3)
        var product10 = Product(1293,"Aperol 1Lts",100,"Descripcion del producto detallada Son vinos jóvenes, frescos, mostrar las características y virtudes particulares de los diferentes racimos que intervienen en cada corte o assemblage cumplen con eficacia esa promesa.","https://firebasestorage.googleapis.com/v0/b/francs-app.appspot.com/o/aperol.webp?alt=media&token=3a00c9ea-c1d5-4c86-b059-a4984be47820",850.0,4)
        var product11 = Product(1294,"Campari 1Lts",100,"Descripcion del producto detallada Son vinos jóvenes, frescos, mostrar las características y virtudes particulares de los diferentes racimos que intervienen en cada corte o assemblage cumplen con eficacia esa promesa.","https://firebasestorage.googleapis.com/v0/b/francs-app.appspot.com/o/camparo.webp?alt=media&token=32ad9f83-e3ba-41e4-b934-f948b45a6e11",750.0,4)
        var product12 = Product(12934,"Gancia 1Lts",100,"Descripcion del producto detallada Son vinos jóvenes, frescos, mostrar las características y virtudes particulares de los diferentes racimos que intervienen en cada corte o assemblage cumplen con eficacia esa promesa.","https://firebasestorage.googleapis.com/v0/b/francs-app.appspot.com/o/gancia.webp?alt=media&token=f08de8c7-e25a-424f-b32a-d615b4534978",650.0,4)


        firebaseDB.collection("productos").add(product)
        firebaseDB.collection("productos").add(product2)
        firebaseDB.collection("productos").add(product3)
        firebaseDB.collection("productos").add(product4)
        firebaseDB.collection("productos").add(product5)
        firebaseDB.collection("productos").add(product6)
        firebaseDB.collection("productos").add(product7)
        firebaseDB.collection("productos").add(product8)
        firebaseDB.collection("productos").add(product9)
        firebaseDB.collection("productos").add(product10)
        firebaseDB.collection("productos").add(product11)
        firebaseDB.collection("productos").add(product12)
    }

    private fun updateProductList () {
        val docRef = firebaseDB.collection("productos")
        docRef.get()
            .addOnSuccessListener { documents ->
                isLoading.value = false
                var products = mutableListOf<Product>()
                for (doc in documents) {
                    val product = doc.toObject(Product::class.java)
                    products.add(product)
                }
                productList.value = products
            }
            .addOnFailureListener { exception ->
                isLoading.value = false
                Log.d("fail", "get failed with ", exception)
            }
    }

    private fun getDrinkTypesList () {
        val docRef = firebaseDB.collection("drinkTypes")
        docRef.get()
            .addOnSuccessListener { documents ->
                var types = mutableListOf<DrinkType>()
                for (doc in documents) {
                    val type = doc.toObject(DrinkType::class.java)
                    types.add(type)
                }
                drinkTypesList.value = types
            }
            .addOnFailureListener { exception ->
                Log.d("fail", "get failed with ", exception)
            }
    }

    fun getProductByDrinkType (type: DrinkType) {
        var isSelected = this.selectedType.value!!.typeId == type.typeId
        if(isSelected){
            selectedType.value = defaultDrinkType
            updateProductList()
        }else {
            val docRef = firebaseDB.collection("productos")
            docRef.get()
                .addOnSuccessListener { documents ->
                    isLoading.value = false
                    var products = mutableListOf<Product>()
                    for (doc in documents) {
                        val product = doc.toObject(Product::class.java)
                        if(product.typeId.equals(type.typeId)){
                            products.add(product)
                        }
                    }
                    productList.value = products
                }
                .addOnFailureListener { exception ->
                    isLoading.value = false
                    Log.d("fail", "get failed with ", exception)
                }
            selectedType.value = type
        }
    }
}