package com.example.inventory.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    //this specifies what to  do in case of a conflict
    suspend fun insert(item: Item)
    //this function can take a long time to execute. So, to be able to
    //call it from a coroutine, it is made to be a suspend function

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM item WHERE id = :id")
    fun getItem(id:Int) : Flow<Item>

    @Query("SELECT * FROM item ORDER BY name ASC")
    fun getItems() : Flow<List<Item>>
}