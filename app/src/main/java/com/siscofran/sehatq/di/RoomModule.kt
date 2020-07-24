package com.siscofran.sehatq.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.siscofran.sehatq.data.ProductDao
import com.siscofran.sehatq.data.ProductDatabase
import com.siscofran.sehatq.data.ProductRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): ProductDatabase {
        return Room.databaseBuilder(application, ProductDatabase::class.java, "Product.db").allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideProductRepository(productDao: ProductDao) : ProductRepository = ProductRepository(productDao)

    @Provides
    @Singleton
    fun provideProductDao(productDatabase: ProductDatabase) : ProductDao = productDatabase.productDao()

    @Provides
    @Singleton
    fun provideViewModelFactory(repository: ProductRepository): ViewModelProvider.Factory {
        return provideViewModelFactory(repository)
    }

}