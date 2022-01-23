package com.example.bikestore.ui.bike_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bicycleStore.data.Bike
import com.example.bicycleStore.data.BikeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class BikeListViewModel @Inject constructor(
    private val repository: BikeRepository
): ViewModel() {
    val bikes = repository.getBikes()

    fun getAllBikes(): LiveData<List<Bike>> = runBlocking {
        bikes.await()
    }

    fun addToCart(id: Int) {
        repository.addToCart(id)
    }

}