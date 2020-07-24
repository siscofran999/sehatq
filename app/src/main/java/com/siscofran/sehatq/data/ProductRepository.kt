package com.siscofran.sehatq.data

import com.siscofran.sehatq.data.model.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productDao: ProductDao) : ProductHelper{

    override fun getAllProduct() : List<Product> = productDao.loadAll()

    override fun insertProduct(product: Product) = productDao.insert(product)

}