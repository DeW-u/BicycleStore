package com.example.bicycleStore.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface BikeDao {
    @Query("SELECT * FROM bikes")
    fun getBikes(): LiveData<List<Bike>>

    @Query("SELECT SUM(price) FROM bikes WHERE in_cart > 0")
    fun getTotalPrice(): LiveData<Int>

    @Query("SELECT * FROM bikes WHERE in_cart > 0")
    fun getCart(): LiveData<List<Bike>>

    @Query("UPDATE bikes SET in_cart = 1 WHERE id = :id")
    fun addToCart(id: Int)

    @Query("UPDATE bikes SET in_cart = 0 WHERE id = :id")
    fun deleteFromCart(id: Int)

    @Query("UPDATE bikes SET in_cart = 0 WHERE in_cart = 1")
    fun checkout()
}