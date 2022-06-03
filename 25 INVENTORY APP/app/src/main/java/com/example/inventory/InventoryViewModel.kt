package com.example.inventory

import android.util.Log
import androidx.lifecycle.*
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class InventoryViewModel(private val itemDao: ItemDao) : ViewModel() {
    private val TAG = "InventoryViewModel"

    val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()

    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    private fun getNewItemEntry(itemName: String, ItemPrice: String, itemCount: String): Item {
        return Item(
            itemName = itemName,
            itemPrice = ItemPrice.toDouble(),
            quantityInStock = itemCount.toInt()
        )
    }

    fun isStockAvailable(item: Item): Boolean {
        return (item.quantityInStock > 0)
    }

    private fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDao.update(item)
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemDao.delete(item)
        }
    }

    fun sellItem(item: Item) {
        if (item.quantityInStock > 0) {
            val newItem = item.copy(quantityInStock = item.quantityInStock - 1)
            updateItem(newItem)
        }
    }

    fun getUpdatedItemEntry(
        itemId: Int,
        itemName: String,
        ItemPrice: String,
        itemCount: String
    ): Item {
        return Item(
            id = itemId,
            itemName = itemName,
            itemPrice = ItemPrice.toDouble(),
            quantityInStock = itemCount.toInt()
        )
    }

    fun updateItem(
        itemId: Int,
        itemName: String,
        itemPrice: String,
        itemCount: String
    ) {
        val updatedItem = getUpdatedItemEntry(itemId, itemName, itemPrice, itemCount)
        updateItem(updatedItem)
    }

    fun addNewItem(itemName: String, ItemPrice: String, itemCount: String) {
        val newItem = getNewItemEntry(itemName, ItemPrice, itemCount)
        insertItem(newItem)
    }

    fun isEntryValid(itemName: String, ItemPrice: String, itemCount: String): Boolean {
        if (itemName.isBlank() || ItemPrice.isBlank() || itemCount.isBlank()) {
            return false
        }
        return true
    }

    fun retrieve(id: Int): LiveData<Item> = itemDao.getItem(id).asLiveData()
}

class InventoryViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(itemDao) as T
        }
        throw IllegalArgumentException("unknown model class")
    }
}

