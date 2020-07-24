package com.siscofran.sehatq.ui.detail

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.siscofran.sehatq.R
import com.siscofran.sehatq.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_search.toolbar
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(DetailViewModel::class.java)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        val gambar = intent.getStringExtra(image)
        val dul = intent.getStringExtra(judul)
        val des = intent.getStringExtra(desc)
        val harga = intent.getStringExtra(price)
        val suka = intent.getIntExtra(like, 0)

        Glide.with(application)
            .load(gambar)
            .into(img)

        txv_title.text = dul
        txv_desc.text = des
        txv_price.text = harga
        if(suka == 1){
            Glide.with(application)
                .load(ContextCompat.getDrawable(application, R.drawable.ic_heart))
                .into(ic_heart)
        }else{
            Glide.with(application)
                .load(ContextCompat.getDrawable(application, R.drawable.ic_heart_black))
                .into(ic_heart)
        }
        btn_buy.setOnClickListener {viewModel.saveRoom(gambar, dul, des, harga, suka)}
        viewModel.getResRoom().observe(this@DetailActivity, Observer {
            if (it){
                Toast.makeText(this@DetailActivity, "Terima Kasih, barang sudah masuk", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    companion object{
        const val image = "image"
        const val desc = "desc"
        const val like = "like"
        const val judul = "title"
        const val price = "price"
    }
}