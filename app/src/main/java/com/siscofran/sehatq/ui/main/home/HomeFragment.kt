package com.siscofran.sehatq.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.siscofran.sehatq.R
import com.siscofran.sehatq.ViewModelProviderFactory
import com.siscofran.sehatq.data.model.Category
import com.siscofran.sehatq.data.model.ProductPromo
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    lateinit var viewModel: HomeViewModel
    private var categoryAdapter: CategoryAdapter? = null
    private var productAdapter: ProductAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(HomeViewModel::class.java)
        viewModel.getData()

        activity?.let {activity ->
            viewModel.error().observe(activity, Observer {
                Toast.makeText(activity, "Maaf, ada message error $it", Toast.LENGTH_SHORT).show()
            })

            viewModel.getCategory().observe(activity, Observer {
                setRvCategory(it)
            })

            viewModel.getProduct().observe(activity, Observer {
                setRvProduct(it)
            })
        }
    }

    private fun setRvProduct(it: List<ProductPromo>) {
        rv_product.layoutManager = LinearLayoutManager(activity)
        productAdapter = ProductAdapter(it)
        rv_product.adapter = productAdapter
        productAdapter?.notifyDataSetChanged()
    }

    private fun setRvCategory(it: List<Category>) {
        rv_category.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL ,false)
        categoryAdapter = CategoryAdapter(it)
        rv_category.adapter = categoryAdapter
        categoryAdapter?.notifyDataSetChanged()
    }
}