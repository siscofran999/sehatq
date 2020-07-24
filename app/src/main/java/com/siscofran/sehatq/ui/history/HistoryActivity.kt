package com.siscofran.sehatq.ui.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.siscofran.sehatq.R
import com.siscofran.sehatq.ViewModelProviderFactory
import com.siscofran.sehatq.ui.main.home.ProductAdapter
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_history.rv
import kotlinx.android.synthetic.main.activity_history.toolbar
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HistoryActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    lateinit var viewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(HistoryViewModel::class.java)
        toolbar.title = "Purchase History"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        viewModel.getProduct()

        viewModel.getData().observe(this, Observer {
            rv.layoutManager = LinearLayoutManager(application)
            val historyAdapter = HistoryAdapter(it)
            rv.adapter = historyAdapter
            historyAdapter.notifyDataSetChanged()
        })
    }
}