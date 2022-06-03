package com.example.forage.data

import androidx.room.*
import com.example.forage.model.Forageable
import kotlinx.coroutines.flow.Flow

@Dao
interface ForageableDao {

    @Query("SELECT * FROM forageable")
    fun getAllForagables() : Flow<List<Forageable>>

    @Query("SELECT * FROM forageable WHERE ID = :id")
    fun getForageableById(id:Long) : Flow<Forageable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForageable(forageable: Forageable)

    @Update
    suspend fun updateForageable(forageable: Forageable)

    @Delete
    suspend fun deleteForageable(forageable: Forageable)
}
//completed instructions