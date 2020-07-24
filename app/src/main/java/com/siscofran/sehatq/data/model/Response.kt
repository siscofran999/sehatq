package com.siscofran.sehatq.data.model

class Response : ArrayList<ResponseItem>()

data class ResponseItem(
    val `data`: Data
)

data class Data(
    val category: List<Category>,
    val productPromo: List<ProductPromo>
)

data class Category(
    val id: Int,
    val imageUrl: String,
    val name: String
)

data class ProductPromo(
    val description: String,
    val id: String,
    val imageUrl: String,
    val loved: Int,
    val price: String,
    val title: String
)