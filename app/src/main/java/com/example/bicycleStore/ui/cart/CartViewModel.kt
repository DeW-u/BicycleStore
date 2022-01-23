package com.example.bikestore.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bicycleStore.data.Bike
import com.example.bicycleStore.data.BikeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: BikeRepository
): ViewModel() {
    val cart = repository.getCart()
    val totalPrice = repository.getTotalPrice()

    fun getCart(): LiveData<List<Bike>> = runBlocking {
        cart.await()
    }

    fun deleteFromCart(id: Int) {
        repository.deleteFromCart(id)
    }

    fun getTotalPrice(): LiveData<Int> = runBlocking {
        totalPrice.await()
    }

    fun checkout() {
        repository.checkout()
    }
}