package com.siscofran.sehatq.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.siscofran.sehatq.R
import com.siscofran.sehatq.data.model.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(private val category: List<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder =
        CategoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))

    override fun getItemCount(): Int = category.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) = holder.bindCategory(category[position])

    class CategoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindCategory(category: Category) {
            Glide.with(itemView.context)
                .load(category.imageUrl)
                .apply(RequestOptions().override(200, 200))
                .into(itemView.img)
            itemView.txv_title.text = category.name
        }

    }
}