package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Item::class],//specifies Item as the only class with the list of entities
    version = 1,//to be increased whenever we make a change to the schema
    exportSchema = false//set false to not keep version history backups
)
abstract class ItemRoomDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile//This means the variable will never be cached.
        //This makes sure that all actions are performed on the updated values.
        private var INSTANCE: ItemRoomDatabase? = null
        fun getDatabase(context: Context): ItemRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        ItemRoomDatabase::class.java,//data type for the database
                        "item_database"//a name for the database
                )
                    .fallbackToDestructiveMigration()//this defines what to do when a schema
                    //change occurs. Here, the entire database is destroyed and rebuilt.
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}