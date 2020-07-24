package com.siscofran.sehatq.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.siscofran.sehatq.data.ApiRepository
import com.siscofran.sehatq.data.model.Category
import com.siscofran.sehatq.data.model.ProductPromo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val category = MutableLiveData<List<Category>>()
    private val product = MutableLiveData<List<ProductPromo>>()
    private val error = MutableLiveData<String>()

    fun getData() {
        compositeDisposable.add(apiRepository.getDataHome()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                category.value = it[0].data.category
                product.value = it[0].data.productPromo
            },{
                error.value = it.message
            }))
    }

    fun getCategory() = category
    fun getProduct() = product
    fun error() = error

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}