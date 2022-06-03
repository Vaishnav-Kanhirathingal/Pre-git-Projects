package com.example.busschedule

import android.app.Application
import com.example.busschedule.database.AppDatabase

class BusScheduleApplication : Application() {
    //since this is an extension of the application class,
    //it gets initiated before anything else
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }
}

