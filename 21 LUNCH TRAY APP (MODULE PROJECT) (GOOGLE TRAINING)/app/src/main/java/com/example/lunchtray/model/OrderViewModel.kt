/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.lunchtray.data.DataSource
import java.text.NumberFormat

class OrderViewModel : ViewModel() {
    val TAG = "orderViewModel_VKP"

    val menuItems = DataSource.menuItems

    private var previousEntreePrice = 0.0
    private var previousSidePrice = 0.0
    private var previousAccompanimentPrice = 0.0

    private val taxRate = 0.08

    private val _entree = MutableLiveData<MenuItem?>()
    val entree: LiveData<MenuItem?> = _entree

    private val _side = MutableLiveData<MenuItem?>()
    val side: LiveData<MenuItem?> = _side

    private val _accompaniment = MutableLiveData<MenuItem?>()
    val accompaniment: LiveData<MenuItem?> = _accompaniment

    private val _subtotal = MutableLiveData(0.0)
    val subtotal: LiveData<String> = Transformations.map(_subtotal) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _total = MutableLiveData(0.0)
    val total: LiveData<String> = Transformations.map(_total) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _tax = MutableLiveData(0.0)
    val tax: LiveData<String> = Transformations.map(_tax) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    fun setEntree(entree: String) {
        if (_entree.value != null) {
            previousEntreePrice = this.entree.value!!.price
            _subtotal.value = _subtotal.value?.minus(previousEntreePrice)
        }
        _entree.value = DataSource.menuItems[entree]
        updateSubtotal(DataSource.menuItems[entree]?.price!!)
        Log.d(TAG, "fun setEntree : entree name = ${_entree.value?.name} and _subtotal.value = ${_subtotal.value}")
    }

    fun setSide(side: String) {
        if (_side.value != null) {
            Log.d(TAG,"if _side.value!null is true")
            previousSidePrice = this.side.value!!.price
            _subtotal.value = _subtotal.value?.minus(previousSidePrice)
            Log.d(TAG,"fun setSide : _subtotal.value after minus = ${_subtotal.value}")//todo - value is correct
        }
        _side.value = DataSource.menuItems[side]
        Log.d(TAG,"fun setSide : value passed to update subtotal = ${_subtotal.value!!.plus(_side.value!!.price)}")
        //updateSubtotal(_subtotal.value!!.plus(_side.value!!.price))
        updateSubtotal(_side.value!!.price)
        Log.d(TAG, "fun setSide : side name = ${_side.value?.name} and _subtotal.value = ${_subtotal.value}")
    }

    fun setAccompaniment(accompaniment: String) {
        if (_accompaniment.value != null) {
            previousAccompanimentPrice = this.accompaniment.value!!.price
            _subtotal.value = _subtotal.value?.minus(previousAccompanimentPrice)
        }
        _accompaniment.value = DataSource.menuItems[accompaniment]
        updateSubtotal(DataSource.menuItems[accompaniment]?.price!!)
        Log.d(TAG, "fun setAccompaniment : accompaniment name = ${_accompaniment.value?.name} and _subtotal.value = ${_subtotal.value}")
    }

    private fun updateSubtotal(itemPrice: Double) {
        if (_subtotal.value != null) {
            Log.d(TAG,"fun updateSubtotal : is not null executed")
            _subtotal.value = _subtotal.value!!.plus(itemPrice)
        } else {
            Log.d(TAG,"fun updateSubtotal : is null executed")
            _subtotal.value = itemPrice
        }
        Log.d(TAG,"subtotal - ${_subtotal.value}, ${subtotal.value}")
        calculateTaxAndTotal()
    }

    fun calculateTaxAndTotal() {
        _tax.value = _subtotal.value!! * taxRate
        _total.value = _subtotal.value!! + _tax.value!!
        Log.d(TAG,"tax set to - ${_tax.value}, ${tax.value} and total set to - ${_total.value}, ${total.value}")
    }

    fun resetOrder() {
        previousEntreePrice = 0.0
        previousSidePrice = 0.0
        previousAccompanimentPrice = 0.0
        _total.value = 0.0
        _subtotal.value = 0.0
        _tax.value = 0.0
        _entree.value = null
        _side.value = null
        _accompaniment.value = null
    }
}
