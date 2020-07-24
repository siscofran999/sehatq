package com.siscofran.sehatq.ui.main.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.siscofran.sehatq.R
import com.siscofran.sehatq.data.model.ProductPromo
import com.siscofran.sehatq.ui.detail.DetailActivity
import com.siscofran.sehatq.ui.detail.DetailActivity.Companion.desc
import com.siscofran.sehatq.ui.detail.DetailActivity.Companion.image
import com.siscofran.sehatq.ui.detail.DetailActivity.Companion.judul
import com.siscofran.sehatq.ui.detail.DetailActivity.Companion.like
import com.siscofran.sehatq.ui.detail.DetailActivity.Companion.price
import kotlinx.android.synthetic.main.item_category.view.img
import kotlinx.android.synthetic.main.item_category.view.txv_title
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(private val product: List<ProductPromo>) : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder =
        ProductHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))

    override fun getItemCount(): Int = product.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) = holder.bindProduct(product[position])

    class ProductHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindProduct(product: ProductPromo) {
            Glide.with(itemView.context)
                .load(product.imageUrl)
                .into(itemView.img)
            if(product.loved == 1){
                Glide.with(itemView.context)
                    .load(ContextCompat.getDrawable(itemView.context, R.drawable.ic_heart))
                    .into(itemView.img_heart)
            }else{
                Glide.with(itemView.context)
                    .load(ContextCompat.getDrawable(itemView.context, R.drawable.ic_heart_black))
                    .into(itemView.img_heart)
            }
            itemView.txv_title.text = product.title

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(image, product.imageUrl)
                intent.putExtra(desc, product.description)
                intent.putExtra(like, product.loved)
                intent.putExtra(judul, product.title)
                intent.putExtra(price, product.price)
                itemView.context.startActivity(intent)

            }
        }

    }
}