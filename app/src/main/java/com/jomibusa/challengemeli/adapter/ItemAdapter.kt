package com.jomibusa.challengemeli.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.jomibusa.challengemeli.R
import com.jomibusa.challengemeli.data.model.Results
import com.jomibusa.challengemeli.databinding.ItemListBinding
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import java.text.NumberFormat

class ItemAdapter(private val onClick: (Results) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(), Filterable {

    private var itemList: List<Results> = listOf()
    private var itemListFilter: List<Results> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemListFilter[position]
        holder.bind(item)
    }

    override fun getItemCount() = itemListFilter.size

    fun submitList(itemList: List<Results>) {
        this.itemList = itemList
        this.itemListFilter = itemList
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemListFilter = itemList
                } else {
                    var resultList = mutableListOf<Results>()
                    resultList = processFilterName(charSearch, resultList)
                    itemListFilter = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = itemListFilter
                filterResults.count = itemListFilter.size
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemListFilter = results?.values as List<Results>
                notifyDataSetChanged()
            }
        }
    }

    private fun processFilterName(
        charSearch: String,
        resultList: MutableList<Results>
    ): MutableList<Results> {
        for (item in itemList) {
            if (item.title.lowercase().contains(charSearch.lowercase())) {
                resultList.add(item)
            }
        }
        return resultList
    }

    inner class ItemViewHolder(
        private val binding: ItemListBinding,
        private val onClick: (Results) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var item: Results? = null

        init {
            binding.cardViewItem.setOnClickListener { item?.let { onClick(it) } }
        }

        fun bind(item: Results) {

            this.item = item

            binding.apply {
                textViewNameItem.text = item.title
                textViewPrice.text = parseToCurrencyFormat(item.price)
                val quantity =
                    "${item.quantity} ${binding.root.context.getString(R.string.text_items_available)}"
                textViewQuantity.text = quantity
                if (item.mercadoPago) {
                    textViewMercadoPago.visibility = View.VISIBLE
                }
                if (item.imageItem.isNotEmpty()) {
                    Picasso.get().load(replaceUrl(item.imageItem))
                        .placeholder(R.drawable.default_item).into(imageViewItem)
                }
            }
        }

        private fun replaceUrl(url: String): String {
            return url.replaceFirst("http", "https")
        }

        private fun parseToCurrencyFormat(price: Double): String {
            val formatter: NumberFormat = DecimalFormat("#,###")
            return formatter.format(price)
        }

    }

}