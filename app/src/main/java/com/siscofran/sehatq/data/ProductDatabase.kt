package com.siscofran.sehatq.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.siscofran.sehatq.data.model.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

}