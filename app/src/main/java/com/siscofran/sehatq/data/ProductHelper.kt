package com.siscofran.sehatq.data

import com.siscofran.sehatq.data.model.Product

interface ProductHelper{
    fun getAllProduct(): List<Product>
    fun insertProduct(product: Product)

}