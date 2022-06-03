package com.example.busschedule.database.schedule

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity this is used by room to identify that this
//class can be used for storing data from the database
@Entity
data class Schedule(
    @PrimaryKey val id: Int,
    //@PrimaryKey is important for room to identify it as a primary key

    @NonNull @ColumnInfo(name = "stop_name") val stopName: String,

    @NonNull @ColumnInfo(name = "arrival_time") val arrivalTime: Int
)

