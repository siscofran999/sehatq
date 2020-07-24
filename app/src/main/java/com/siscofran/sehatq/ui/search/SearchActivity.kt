package com.siscofran.sehatq.ui.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.siscofran.sehatq.R
import com.siscofran.sehatq.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : DaggerAppCompatActivity() {

    private val TAG = SearchActivity::class.qualifiedName
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(SearchViewModel::class.java)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        sv.isIconified = false
        sv.isFocusable = true
        sv.requestFocusFromTouch()

        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText?.length!! >= 1){
                    viewModel.search(newText)
                }else{
                    rv.visibility = View.GONE
                }
                return true
            }
        })

        viewModel.error().observe(this, Observer {
            Toast.makeText(application, "Maaf, ada message error $it", Toast.LENGTH_SHORT).show()
        })

        viewModel.getProduct().observe(this, Observer {
            rv.visibility = View.VISIBLE
            rv.layoutManager = LinearLayoutManager(application)
            val searchAdapter = SearchAdapter(it)
            rv.adapter = searchAdapter
            searchAdapter.notifyDataSetChanged()
        })
    }

}