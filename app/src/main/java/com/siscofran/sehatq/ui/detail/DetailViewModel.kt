package com.siscofran.sehatq.ui.detail

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.siscofran.sehatq.data.ProductRepository
import com.siscofran.sehatq.data.model.Product
import javax.inject.Inject


class DetailViewModel @Inject constructor(private val productRepository: ProductRepository): ViewModel() {

    private val response = MutableLiveData<Boolean>()

    fun saveRoom(gambar: String?, judul: String?, deskripsi: String?, harga: String, suka: Int) {
        AsyncTask.execute { // Insert Data
            productRepository.insertProduct(Product(image = gambar,title = judul,desc = deskripsi, price = harga, like = suka))
        }
        response.value = true
    }

    fun getResRoom() = response

}