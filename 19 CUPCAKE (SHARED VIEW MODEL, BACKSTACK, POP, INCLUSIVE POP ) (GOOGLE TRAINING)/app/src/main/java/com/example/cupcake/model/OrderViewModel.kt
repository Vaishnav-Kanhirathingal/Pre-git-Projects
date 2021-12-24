package com.example.cupcake.model

import android.icu.text.NumberFormat
import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel() {
    private val TAG = "ORDER VIEW MODEL"

    var _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity     //getter

    var _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor      //getter

    var _date = MutableLiveData<String>()
    val date: LiveData<String> = _date          //getter

    var _price = MutableLiveData<Double>(0.0)
    val price: LiveData<String> = Transformations.map(_price){
        NumberFormat.getCurrencyInstance().format(it)
    }//this requires lifecycle owner to be not null.

    val dateOptions:List<String> = getPickUpOptions()
    // this is the getter of the value.

    init { resetOrder() }

    fun setQuantity(noOfCupCakes: Int) {
        _quantity.value = noOfCupCakes
        updatePrice()
    }

    fun setFlavor(flavourGiven: String) { _flavor.value = flavourGiven }

    fun setDate(dateGiven: String) {
        _date.value = dateGiven
        updatePrice()
    }

    fun hasNoFlavor(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    fun getPickUpOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    private fun updatePrice() {
        _price.value = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
        if (date.value == dateOptions[0]) {
            _price.value = (_price.value ?: 0.0) + PRICE_FOR_SAME_DAY_PICKUP
        }
        Log.d(TAG,"PRICE.VALUE = ${price.value}, _PRICE.VALUE = ${_price.value}" +
                " FLAVOR = ${flavor.value}, DATE = ${date.value}")
    }

    fun resetOrder() {
        _flavor.value = ""
        _date.value = ""
        _quantity.value = 0
        _date.value = dateOptions[0]
        _price.value = 0.0
    }
}