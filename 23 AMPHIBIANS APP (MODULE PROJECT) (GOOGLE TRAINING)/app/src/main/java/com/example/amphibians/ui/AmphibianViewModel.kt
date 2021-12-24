package com.example.amphibians.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.network.Amphibian
import com.example.amphibians.network.AmphibianApi
import kotlinx.coroutines.launch
import java.lang.Exception

enum class AmphibianApiStatus { LOADING, ERROR, DONE }

private val TAG = "AmphibianViewModel"

class AmphibianViewModel : ViewModel() {
    //Create properties to represent MutableLiveData and LiveData for the API status
    var _status = MutableLiveData<AmphibianApiStatus>()
    val status: LiveData<AmphibianApiStatus> = _status

    //Create properties to represent MutableLiveData and LiveData for a list of amphibian objects
    var _amphibian = MutableLiveData<Amphibian>()
    val amphibian: LiveData<Amphibian> = _amphibian

    var _amphibianList = MutableLiveData<List<Amphibian>>()
    val amphibianList: LiveData<List<Amphibian>> = _amphibianList

    init {
        _status.value = AmphibianApiStatus.LOADING
        getAmphibianList()
    }

    private fun getAmphibianList() {
        viewModelScope.launch {
            try {
                _amphibianList.value = AmphibianApi.retrofitService.getAmphibians()
                _status.value = AmphibianApiStatus.DONE
            }
            catch (e: Exception) {
                _amphibianList.value = listOf()
                _status.value = AmphibianApiStatus.ERROR
            }
        }
    }
    //Create properties to represent MutableLiveData and LiveData for a single amphibian object.
    //This will be used to display the details of an amphibian when a list item is clicked

    //Create a function that gets a list of amphibians from the api service and sets the
    //status via a Coroutine

    fun onAmphibianClicked(amphibian: Amphibian) {
        Log.d(TAG,"onAmphibianClicked function called. value set - ${amphibian.name}")
        //Set the amphibian object
        _amphibian.value = amphibian
    }
}
