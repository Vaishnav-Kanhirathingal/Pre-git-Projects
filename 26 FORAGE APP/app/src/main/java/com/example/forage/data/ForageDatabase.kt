package com.example.forage.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.forage.model.Forageable

//create the database with all necessary annotations, methods, variables, etc.
@Database(
    entities = [Forageable::class],
    version = 1,
    exportSchema = false
)
abstract class ForageDatabase : RoomDatabase() {

    abstract fun forgableDao(): ForageableDao

    companion object {
        @Volatile
        private var INSTANCE: ForageDatabase? = null
        fun getDatabase(context: Context): ForageDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        ForageDatabase::class.java,
                        "forage_database"
                    ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
//completed instructions