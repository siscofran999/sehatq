package com.siscofran.sehatq.ui.history

import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.siscofran.sehatq.data.ProductRepository
import com.siscofran.sehatq.data.model.Product
import javax.inject.Inject

class HistoryViewModel @Inject constructor(private val productRepository: ProductRepository): ViewModel() {

    private val data = MutableLiveData<List<Product>>()

    fun getProduct() {
        data.value = productRepository.getAllProduct()
    }

    fun getData() = data

}