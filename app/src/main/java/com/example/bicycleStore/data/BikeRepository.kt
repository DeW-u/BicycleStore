package com.example.bicycleStore.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class BikeRepository(
    private val dao: BikeDao
) {
    fun getBikes(): Deferred<LiveData<List<Bike>>> = CoroutineScope(Dispatchers.IO).async {
        dao.getBikes()
    }

    fun getTotalPrice(): Deferred<LiveData<Int>> = CoroutineScope(Dispatchers.IO).async {
        dao.getTotalPrice()
    }

    fun getCart(): Deferred<LiveData<List<Bike>>> = CoroutineScope(Dispatchers.IO).async {
        dao.getCart()
    }

    fun addToCart(id: Int) = CoroutineScope(Dispatchers.IO).launch {
        dao.addToCart(id)
    }

    fun deleteFromCart(id: Int) = CoroutineScope(Dispatchers.IO).launch {
        dao.deleteFromCart(id)
    }

    fun checkout() = CoroutineScope(Dispatchers.IO).launch {
        dao.checkout()
    }

}