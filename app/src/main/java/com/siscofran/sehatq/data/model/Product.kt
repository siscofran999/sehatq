package com.siscofran.sehatq.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

import androidx.room.PrimaryKey


@Entity(tableName = "product")
class Product (
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "image") var image: String? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "desc") var desc: String? = null,
    @ColumnInfo(name = "price") var price: String? = null,
    @ColumnInfo(name = "like") var like: Int? = 0
)