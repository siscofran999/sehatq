package com.siscofran.sehatq.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.siscofran.sehatq.data.ApiRepository
import com.siscofran.sehatq.data.model.ProductPromo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val products = MutableLiveData<List<ProductPromo>>()
    private val error = MutableLiveData<String>()

    fun search(query: String?) {
        compositeDisposable.add(apiRepository.getDataHome()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                products.value = it[0].data.productPromo.filter {product ->
                    product.title.contains("$query",ignoreCase = true)
                }
            },{
                error.value = it.message
            }))
    }

    fun getProduct() = products
    fun error() = error

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}