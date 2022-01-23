package com.example.bicycleStore.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Bike::class],
    version = 1
)
abstract class BikeDatabase: RoomDatabase() {
    abstract val dao: BikeDao
}