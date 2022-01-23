package com.example.bicycleStore.di

import android.app.Application
import androidx.room.Room
import com.example.bicycleStore.data.BikeDatabase
import com.example.bicycleStore.data.BikeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBikeDatabase(app: Application): BikeDatabase {
        return Room.databaseBuilder(
            app,
            BikeDatabase::class.java,
            "bike_db"
        ).createFromAsset("databases/bike_db.db").build()
    }

    @Provides
    @Singleton
    fun provideBikeRepository(db: BikeDatabase): BikeRepository {
        return BikeRepository(db.dao)
    }
}