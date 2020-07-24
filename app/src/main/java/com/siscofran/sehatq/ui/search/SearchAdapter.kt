package com.siscofran.sehatq.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.siscofran.sehatq.R
import com.siscofran.sehatq.data.model.ProductPromo
import kotlinx.android.synthetic.main.item_search.view.*

class SearchAdapter(private val product: List<ProductPromo>) : RecyclerView.Adapter<SearchAdapter.SearchHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder =
        SearchHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false))

    override fun getItemCount(): Int = product.size

    override fun onBindViewHolder(holder: SearchHolder, position: Int) = holder.bindSearch(product[position])
        
    class SearchHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindSearch(productPromo: ProductPromo) {
            Glide.with(itemView.context)
                .load(productPromo.imageUrl)
                .apply(RequestOptions().override(200, 200))
                .into(itemView.img)

            itemView.txv_title.text = productPromo.title
            itemView.txv_price.text = productPromo.price
        }

    }
}