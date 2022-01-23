package com.example.bicycleStore.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bikes")
data class Bike(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val producer: String,
    val price: Int,
    val image_url: String,
    val in_cart: Int

)
