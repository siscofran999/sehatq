package com.siscofran.sehatq.ui.history

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.siscofran.sehatq.R
import com.siscofran.sehatq.data.model.Product
import com.siscofran.sehatq.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_search.view.*

class HistoryAdapter(private val product: List<Product>) : RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder =
        HistoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false))

    override fun getItemCount(): Int = product.size

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) = holder.bindHistory(product[position])

    class HistoryHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindHistory(product: Product) {
            Glide.with(itemView.context)
                .load(product.image)
                .apply(RequestOptions().override(200, 200))
                .into(itemView.img)

            itemView.txv_title.text = product.title
            itemView.txv_price.text = product.price

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.image, product.image)
                intent.putExtra(DetailActivity.desc, product.desc)
                intent.putExtra(DetailActivity.like, product.like)
                intent.putExtra(DetailActivity.judul, product.title)
                intent.putExtra(DetailActivity.price, product.price)
                itemView.context.startActivity(intent)
            }
        }

    }
}